<template>
  <Header title="Fahrzeugvermietung"/>

  <div class="float-end">
        <button class="btn btn-outline-primary" v-if=" islessor === 'true'" @click="lendVehicles">
            vermietete Fahrzeuge
        </button>
    </div>
  <div class="float-end">
        <button class="btn btn-outline-primary" @click="myVehicles">
            gemietete Fahrzeuge
        </button>
    </div>
  
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
    getCookie() {
    var requestOptions = {
      method: 'GET',
      redirect: 'follow',
      credentials: "include"
    };

    fetch("http://server.it-humke.de:9002/fahrzeugvermietung/get", requestOptions)
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
