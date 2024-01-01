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
  }

}

</script>

<template>
  <v-text-field
      :label=label
      :value=value
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