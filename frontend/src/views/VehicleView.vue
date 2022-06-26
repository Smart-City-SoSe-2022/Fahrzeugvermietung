<template>
  <p class="containerOutside" >
    <div>
        <h1>Elektro-{{vehicle.type}}</h1>
        Modell: {{vehicle.modell}}
        <br><br><br>
    </div>
    <div>
        max. Reichweite: {{vehicle.distance}}km
        <p>max. Geschwindigkeit: {{vehicle.maxSpeed}}km/h</p>
    </div>
    <div>
      <h4>Kosten:</h4>
        <label>
          <input type="radio" name="radioPrice" v-model="prices" id="day" @change="onChangeDay()">
          {{vehicle.priceDay}}€ pro Tag
        </label><br>
        <label>
          <input type="radio" name="radioPrice" v-model="prices" id="week" @change="onChangeWeek">
          {{vehicle.priceWeek}}€ pro Woche
        </label><br>
        <label>
          <input type="radio" name="radioPrice" v-model="prices" id="month" @change="onChangeMonth">
          {{vehicle.priceMonth}}€ pro Monat
        </label><br><br>
        <button class="btn btn-outline-primary" @click="createContract">
          Mieten
        </button>
    </div>
  </p>
</template>

<script>

export default {
  props: [
    'id'
  ],
  data() {
    return {
      vehicle: Object,
      vehiclePrice: Number,
      vehicleReturnDate: String
    }
  },
  methods: {
    onChangeDay() {
      var today = new Date();
      today.setDate(today.getDate()+1)
      var price = this.vehicle.priceDay;
      var date = today.getDate()+'.'+today.getMonth()+'.'+today.getFullYear();
      this.vehiclePrice = price;
      this.vehicleReturnDate = date;
    },
    onChangeWeek() {
      var today = new Date();
      today.setDate(today.getDate()+7)
      var price = this.vehicle.priceWeek;
      var date = today.getDate()+'.'+today.getMonth()+'.'+today.getFullYear();
      this.vehiclePrice = price;
      this.vehicleReturnDate = date;
    },
    onChangeMonth() {
      var today = new Date();
      var price = this.vehicle.priceMonth;
      var date = today.getDate()+'.'+(today.getMonth()+1)+'.'+today.getFullYear();
      this.vehiclePrice = price;
      this.vehicleReturnDate = date;
    },
    async fetchVehicles() {
      const res = await fetch('http://server.it-humke.de:9002/fahrzeugvermietung/fahrzeug/'+this.id)

      const data = await res.json()

      return data
    },
    async createContract() {
      const body = {
        priceOption: this.vehiclePrice,
        returnDate: this.vehicleReturnDate,
      }
      console.log(body)

      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body),
        redirect: "follow",
        credentials: "include"
      }
      console.log(requestOptions)
      fetch('http://server.it-humke.de:9002/fahrzeugvermietung/fahrzeug/'+this.id+'/createContract', requestOptions)
      .then(() => this.$router.push({
        name: "home"
      }))
      
    }
  },
  async created() {
    this.vehicle = await this.fetchVehicles()
  }
}
</script>

<style scoped>
    div {
        max-width: 300px;
        overflow: auto;
        min-height: 100px;
        padding: 10px;
        border-radius: 5px;
    }
    .containerOutside {
        margin: auto;
        align-content: center;
        justify-content: center;
        overflow: auto;
        border: 1px solid black;
        padding: 5px;
        border-radius: 5px;
        margin-inline: 600px;
        margin-block: 50px;
    }
</style>
