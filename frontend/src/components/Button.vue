<script>
  export default {

    props : {

      name : {
        type : String,
        required : true
      },

      event : {
        type : Object,
        require : true
      }

    }

  }
</script>

<template>
  <v-btn @click="$emit('event', event )" margin="15 px" :variant="'outlined'" :text="name"></v-btn>
</template>

<style scoped>

</style>