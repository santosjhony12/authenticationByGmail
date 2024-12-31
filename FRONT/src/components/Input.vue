<template>
    <div class="container-input">
        <label for="input">{{ label }}</label>
        <input :type="type" name="" id="" :placeholder="placeholder"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        >
    </div>
</template>

<script lang="ts" setup>

const props = defineProps<{
    label: string,
    placeholder: string,
    type: string,
    modelValue: string | number | null
}>();
const emits = defineEmits(['update:modelValue']);
</script>

<style scoped>
.container-input{
    display: flex;
    flex-direction: column;
}
input{
    width: 20vw;
    padding: 1em;
    border-radius: 10px;
    border: 1px solid #ccc;
    border-radius: 8px;
    outline: none;
    background-color: #f9f9f9;
    color: #333;
    transition: all 0.3s ease-in-out;
    box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    
}
</style>