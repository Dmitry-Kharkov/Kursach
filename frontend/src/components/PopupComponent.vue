<template>
<div class="popup">
  <div class="popup__content">
    <div class="header">
      <h2>{{ title }}</h2>
      <button type="button" v-if="showButtonClose" @click="$emit('close')">X</button>
    </div>
    <slot name="content"></slot>
  </div>
</div>
</template>

<style>
.popup {
  background-color: rgba(0, 0, 0, 0.5);
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0px;
  left: 0px;
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999999;
}
.popup__content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  min-width: 300px;
}
</style>

<script>
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'PopupComponent',
  props: {
    title: String,
    showButtonClose: Boolean
  },
  emits: ['close']
});
</script>