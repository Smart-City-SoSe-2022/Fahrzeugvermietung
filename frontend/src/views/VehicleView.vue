<template>
    <div class="container">
        <h1>elektro {{vehicle.type}}</h1>
        Modell: {{vehicle.modell}}
        <br><br><br>
    
    </div>
    <div class="container">
        <!--Bilder laden-->
    </div>
    <div></div>
    <div class="container">
        max. Reichweite: {{vehicle.distance}}km
        <p>max. Geschwindigkeit: {{vehicle.maxSpeed}}km/h</p>
    </div>
    <div class="space">
        <p>[]</p>
        <p>[]</p>
        <p>[]</p>
    </div>
    <div class="nospace">
        Kosten:
        <p>{{vehicle.priceDay}}€ pro Tag</p>
        <p>{{vehicle.priceWeek}}€ pro Woche</p>
        <p>{{vehicle.priceMonth}}€ pro Monat</p>
    </div>
</template>

<script>

export default {
  props: [
    'id'
  ],
  data() {
    return {
      vehicle: Object
    }
  },
  methods: {
    async fetchVehicles() {
      const res = await fetch('http://server.it-humke.de:9002/fahrzeugvermietung/fahrzeug/'+this.id)

      const data = await res.json()

      return data
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
            margin: 5px auto;
            overflow: auto;
            min-height: 100px;
            padding: 10px;
            border-radius: 5px;
    }
    .container {
        display: inline-block;
        justify-content: space-between;
        margin-left: 100px;
    }
    .space {
        display: inline-block;
        justify-content: space-between;
        margin-left: 400px;
    }
    .nospace {
        display: inline-block;
        justify-content: space-between;
    }
</style>
