<template>
    <div>
        <div class="container" :key="vehicle.id" v-for="vehicle in vehicles.filter(item => item.type === 'Fahrrad')">
            <Vehicle @click="viewVehicle(vehicle.id)" :vehicle="vehicle"/>
        </div>
    </div>
    <div>
        <div class="container" :key="vehicle.id" v-for="vehicle in vehicles.filter(item => item.type === 'Roller')">
        <Vehicle @click="viewVehicle(vehicle.id)" :vehicle="vehicle"/>
        </div>
    </div>
    <div>
        <div class="container" :key="vehicle.id" v-for="vehicle in vehicles.filter(item => item.type === 'Auto')">
        <Vehicle @click="viewVehicle(vehicle.id)" :vehicle="vehicle"/>
        </div>
    </div>
</template>

<script>
import Vehicle from './Vehicle.vue'

export default {
    name: 'Vehicles',
    props: {
        vehicles: Array,
    },
    components: {
        Vehicle
    },
    methods: {
        viewVehicle(id) {
            this.$router.push('/fahrzeug/'+id)
        }
    }
}
</script>

<style scoped> 
    .container {
        max-width: 300px;
        margin: 5px auto;
        overflow: auto;
        min-height: 100px;
        border: 1px solid steelblue;
        background: #dbd8d8;
        padding: 10px;
        border-radius: 5px;
        display: inline-block;
        justify-content: space-between;
        margin-left: 10px;
    }
</style>
