<template>
    <div class="containerOutside">
        <div :key="vehicle.id" v-for="vehicle in vehicles">
            <div class="container">
                <h3>Elektro-{{ vehicle.type }}</h3>
                <p>Modell: {{ vehicle.modell }}</p>
                <p>Farbe: {{ vehicle.color }}</p>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'MyVehicles',
    props: {
        vehicles: Array,
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
        margin-left: 10px;
    }
    .containerOutside {
        margin: 20px auto;
        overflow: auto;
        border: 1px solid black;
        padding: 5px;
        border-radius: 5px;
        margin-inline: 200px;
    }
</style>

