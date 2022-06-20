<template>
    <div class="container" :key="vehicle.id" v-for="vehicle in vehicles">
        <Vehicle :vehicle="vehicle" />
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
    }
}
</script>

<style scoped>
    div {
        display: inline-block;
        justify-content: space-between;
        margin-left: 10px;
    } 
    .container {
        max-width: 300px;
        margin: 30px auto;
        overflow: auto;
        min-height: 100px;
        border: 1px solid steelblue;
        padding: 30px;
        border-radius: 5px;
    }
</style>
