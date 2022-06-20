<template>
  <Header/>
  <Vehicles :vehicles="vehicles" />
</template>

<script>
import Header from './components/Header.vue'
import Vehicles from './components/Vehicles.vue'

export default {
  name: 'App',
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
      const res = await fetch('http://server.it-humke.de:9002/fahrzeugvermietung')

      const data = await res.json()

      return data
    }
  },
  async created() {
    this.vehicles = await this.fetchVehicles()
  }
}
</script>
