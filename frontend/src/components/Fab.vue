<script>
export default {

}
</script>

<template>

  <v-btn
      class = "fab"
      fab
      color="primary"
      position="fixed"
      location="right bottom"
      size="x-large"
      @click="$emit('fab_action')">
    <v-icon>mdi-plus</v-icon>
  </v-btn>


</template>

<style>

.fab{
  margin: 50px;
}

</style>