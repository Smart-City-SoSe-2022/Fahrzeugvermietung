<template>
  <Header title="Fahrzeugvermietung"/>
  <Vehicles :vehicles="vehicles" />
</template>

<script>
import Header from '../components/Header.vue'
import Vehicles from '../components/Vehicles.vue'

export default {
  name: 'Home',
  components: {
    Header,
    Vehicles
  },
  data() {
    return {
      vehicles: []
    }
  },
  methods: {
    async fetchVehicles() {
      var myHeaders = new Headers();
      myHeaders.append("Content-Type","application/json");

      var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow',
        credentials: "include"
      }
      const res = await fetch('http://localhost:8080/fahrzeugvermietung', requestOptions)

      const data = await res.json()

      return data
    }
  },
  async created() {
    this.vehicles = await this.fetchVehicles()
  }
}

</script>
