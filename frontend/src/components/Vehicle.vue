<template>
    <div class="vehicle">
        <h3>Elektro-{{ vehicle.type }}</h3>
        <p>Modell: {{ vehicle.modell }}</p>
        <p>Farbe: {{ vehicle.color }}</p>
    </div>
</template>

<script>
export default {
    name: 'Vehicle',
    props: {
        vehicle: Object
    }
}
</script>