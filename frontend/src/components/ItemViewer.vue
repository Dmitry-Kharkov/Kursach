<script>

import {blue} from "vuetify/util/colors";

export default {
  computed: {
    blue() {
      return blue
    }
  },

  props : {

    id : {
      type : String,
      required : true
    },

    item : {
      type : Object,
      require : true
    }

  }

}

</script>

<template>

  <v-card :variant="'outlined'" height="40%" >
    <v-card-text>
      <div>{{ item.name }}</div>
      <div>{{ item.description }}</div>
      <v-col class="text-right">
        <v-btn @click="$emit('item_edit', item )" :variant="'outlined'">Редактировать</v-btn>
        <v-btn @click="$emit('item_delete', id)" :variant="'outlined'">Удалить</v-btn>
      </v-col>
<!--      <div><strong>ID:</strong> {{ id }}</div>-->
<!--      <div>{{ item.description }}</div>-->
<!--      <div><strong>Создан:</strong> {{ item.created }}</div>-->
<!--      <div><strong>Изменен:</strong> {{ item.modified }}</div>-->
    </v-card-text>
  </v-card>
</template>

<style>

</style>