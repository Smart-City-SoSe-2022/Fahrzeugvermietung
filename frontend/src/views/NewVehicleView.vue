<template>
<div class="float-start">
    <img class="leftBack" @click="this.$router.push('/')" src="../assets/back.png" widht="50" height="50">
</div>
  <p class="containerOutside" >
    <div>
      <h4>Typ:</h4>
        <label>
          <input type="radio" name="radioType" v-model="types" id="bike" @change="onChangeBike">
          Fahrrad
        </label><br>
        <label>
          <input type="radio" name="radioType" v-model="types" id="scooter" @change="onChangeScooter">
          Roller
        </label><br>
        <label>
          <input type="radio" name="radioType" v-model="types" id="car" @change="onChangeCar">
          Auto
        </label>
    </div>
    <br>
    <p>
        Hersteller und Modell: <input id="modell" value="BMW i3"/>
    </p>
    <p>
        Farben: <input id="color" value="Schwarz"/>
    </p>
    <p>
        max. Reichweite: <input id="distance" value="350"/>km
    </p>
    <p>
        max. Geschwindigkeit: <input id="maxSpeed" value="150"/>km/h
    </p>
    <p>
        Kilometerstand: <input id="mileage" value="3598"/>km
    </p>
    <p>
        Tagespreis: <input id="priceDay" value="10"/>€
    </p>
    <p>
        Wochenpreis: <input id="priceWeek" value="50"/>€
    </p>
    <p>
        Monatspreis: <input id="priceMonth" value="200"/>€
    </p>
    
    <br>
    <button class="btn btn-outline-danger" @click="this.$router.push('/')">
        Abbrechen
    </button>
    <div class="float-end">
    <button class="btn btn-outline-primary" @click="createContract">
        Erstellen
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
      type: String,
      modell: String,
      color: String,
      distance: Number,
      maxSpeed: Number,
      mileage: Number,
      priceDay: Number,
      priceWeek: Number,
      priceMonth: Number,
    }
  },
  methods: {
    onChangeBike() {
      this.type = "Fahrrad";
    },
    onChangeScooter() {
      this.type = "Roller";
    },
    onChangeCar() {
      this.type = "Auto";
    },
    async createContract() {
      const body = {
        type: this.type,
        modell: document.getElementById('modell').value,
        color: document.getElementById('color').value,
        distance: document.getElementById('distance').value,
        maxSpeed: document.getElementById('maxSpeed').value,
        mileage: document.getElementById('mileage').value,
        priceDay: document.getElementById('priceDay').value,
        priceWeek: document.getElementById('priceWeek').value,
        priceMonth: document.getElementById('priceMonth').value,
        availability: true
      }

      const requestOptions = {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(body),
        redirect: "follow",
        credentials: "include"
      }
      
      fetch('http://server.it-humke.de:9002/fahrzeugvermietung/neues_fahrzeug', requestOptions)
      .then(() => this.$router.push({
        name: "home"
      }))
    }
  },
  async created() {
    this.type = "Auto";
  }
}
</script>

<style scoped>
    .containerOutside {
        margin: auto;
        align-content: center;
        justify-content: center;
        overflow: auto;
        border: 1px solid black;
        background: lightcyan;
        padding: 5px;
        border-radius: 5px;
        margin-inline: 500px;
        margin-block: 50px;
    }
    .leftBack{
      padding-left: 80px;
    }
</style>
