<template>
  <Header title="vermietete Fahrzeuge"/>
  
  <LendVehicles :vehicles="vehicles" />
</template>

<script>
import Header from '../components/Header.vue'
import LendVehicles from '../components/LendVehicles.vue'

export default {
  name: 'Home',
  components: {
    Header,
    LendVehicles
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
      const res = await fetch('http://server.it-humke.de:9002/fahrzeugvermietung/vermietete_fahrzeuge', requestOptions)

      const data = await res.json()

      return data
    }
  },
  async created() {
    this.vehicles = await this.fetchVehicles()
  }
}

</script>
