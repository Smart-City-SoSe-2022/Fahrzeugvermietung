<template>
    <div class="containerOutside">
        <div :key="vehicle.id" v-for="vehicle in vehicles">
            <div class="container">
                <div class="containerClear">
                    <h3>Elektro-{{ vehicle.type }}</h3>
                    Modell: {{ vehicle.modell }}<br>
                    Farbe: {{ vehicle.color }}
                </div>
                <div class="containerInside">
                    Kilometerstand: {{vehicle.mileage}}km<br>
                    Maximale Geschwindigkeit: {{vehicle.maxSpeed}}km/h<br>
                    Maximale Reichweite: {{vehicle.distance}}km<br>
                </div>
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
        margin: 5px auto;
        overflow: auto;
        min-height: 100px;
        border: 1px solid steelblue;
        background: #dbd8d8;
        padding: 10px;
        border-radius: 10px;
    }
    .containerOutside {
        margin: 20px auto;
        overflow: auto;
        border: 1px solid black;
        background: lightgoldenrodyellow;
        padding: 5px;
        border-radius: 5px;
        margin-inline: 450px;
    }
    .containerInside{
        padding: 15px;
        border: 1px solid seagreen;
        background: lightcyan;
        border-radius: 5px;
        display: inline-block;
        justify-content: space-between;
    }
    .containerClear{
        min-width: 350px;
        max-width: 450px;
        display: inline-block;
        justify-content: space-between;
    }
</style>

