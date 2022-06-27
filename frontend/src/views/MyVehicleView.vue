<template>
  <Header title="gemietete Fahrzeuge"/>
  <div class="float-start">
    <img class="leftBack" @click="this.$router.push('/')" src="../assets/back.png" widht="50" height="50">
  </div>
    <MyVehicles :vehicles="vehicles"/>
</template>

<script>
import Header from '../components/Header.vue'
import MyVehicles from '../components/MyVehicles.vue'

export default {
  name: 'Home',
  components: {
    Header,
    MyVehicles
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
      const res = await fetch('http://server.it-humke.de:9002/fahrzeugvermietung/gemietete_fahrzeuge', requestOptions)

      const data = await res.json()

      return data
    }
  },
  async created() {
    this.vehicles = await this.fetchVehicles()
  }
}

</script>

<style scoped>
  .leftBack {
    padding-left: 80px;
  }
</style>
