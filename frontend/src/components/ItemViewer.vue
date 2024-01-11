<script>

export default {

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

  <v-card :variant="'outlined'" >
    <v-card-title>{{ item.name }}</v-card-title>
    <v-card-text>
      <div><strong>ID:</strong> {{ id }}</div>
      <div>{{ item.description }}</div>
      <div><strong>Создан:</strong> {{ item.created }}</div>
      <div><strong>Изменен:</strong> {{ item.modified }}</div>

    </v-card-text>
    <v-card-actions>
      <v-btn @click="$emit('item_edit', item )" :variant="'outlined'">Редактировать</v-btn>
      <v-btn @click="$emit('item_delete', id)" :variant="'outlined'">Удалить</v-btn>
    </v-card-actions>
  </v-card>
</template>

<style>

</style>