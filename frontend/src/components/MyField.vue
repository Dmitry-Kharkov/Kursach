<script>

export default {

  props: {

    label: {
      type: String,
      required: true
    },

    value: {
      type : String,
      required: true
    }
  },

  computed : {

    modelValue(){
      return this.value
    }


  }

}

</script>

<template>
  <v-text-field
      :label=label
      v-model=modelValue
      variant="outlined"
      class="field"
      @input="$emit('update:modelValue', $event.target.value)"
  ></v-text-field>
</template>

<style>

.field{
  margin: 25px;
}

</style>