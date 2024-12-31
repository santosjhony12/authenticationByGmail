<template>
    <div class="container-alerta">
        {{ message }}
    </div>
</template>

<script lang="ts" setup>
const props = defineProps<{
    message: string
}>();
</script>

<style scoped>
.container-alerta{
    position: fixed;
    color: black;
    top: 1em;
    right: 0;
    background-color: #99ff74;
    padding: 10px 20px;
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
    border-radius: 5px 0 0 5px;
    z-index: 1000;
    opacity: 1;
    transform: translateX(0);
    transition: opacity 0.5s ease, transform 0.5s ease;
}
</style>