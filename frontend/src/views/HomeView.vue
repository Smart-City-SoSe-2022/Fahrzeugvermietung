<template>
  <Header title="Fahrzeugvermietung"/>

  <div class="float-start">
    <img class="leftBack" @click="backToPortal()" src="../assets/back.png" widht="50" height="50">
  </div>
  <div class="float-end">
        <button class="btn btn-outline-primary" v-if="checkEqual(islessor)" @click="lendVehicles">
            vermietete Fahrzeuge
        </button>
        <button class="btn btn-outline-primary" @click="myVehicles">
            gemietete Fahrzeuge
        </button>
    </div>
  
  <Vehicles :vehicles="vehicles" />
  <div v-if=(checkEqual(islessor))>
    <div class="position-absolute start-50">
      <img src="../assets/plus.png" @click="createNewVehicle" width="100" height="100">
    </div>
  </div>
  
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
      vehicles: [],
      islessor: String
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
      const res = await fetch('http://server.it-humke.de:9002/fahrzeugvermietung', requestOptions)

      const data = await res.json()

      return data
    },
    backToPortal(){
      window.location.href='http://server.it-humke.de:8001'
    },
    checkEqual(string) {
      return string=="true"
    },
    createNewVehicle() {
      this.$router.push('/neues_fahrzeug')
    },
    async isLessor() {
    var requestOptions = {
      method: 'GET',
      redirect: 'follow',
      credentials: "include"
    };

    const res = await fetch("http://server.it-humke.de:9002/fahrzeugvermietung/user", requestOptions)

    const data = await res.text()

    return data
    },
    lendVehicles() {
            this.$router.push('/vermietete_fahrzeuge')
    },
    myVehicles() {
            this.$router.push('/gemietete_fahrzeuge')
    }
  },
  async created() {
    this.vehicles = await this.fetchVehicles()
    this.getCookie()
    this.islessor = await this.isLessor()
  }
}

</script>

<style scoped>
  .leftBack {
    padding-left: 80px;
  }
</style>
