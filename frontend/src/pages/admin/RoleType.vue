<script>

import roleTypeController from "@/controllers/RoleTypeController";

export default {

  data() {
    return {

      roleTypes: []

    }
  },

  mounted() {

    roleTypeController.getAll()
        .then(response => this.roleTypes = response.data)
        .catch(() => alert("Произошла ошибка при загрузке типов ролей"))

  }

}

</script>

<template>

  <v-card :variant="'outlined'" v-for="roleType in roleTypes" :key="roleType.roleTypeId">

    <v-card-title>{{ roleType.name }}</v-card-title>
    <v-card-text>
      <div><strong>ID:</strong> {{ roleType.roleTypeId }}</div>
      <div>{{ roleType.description }}</div>
      <div><strong>Создан:</strong> {{ roleType.created }}</div>
      <div><strong>Изменен:</strong> {{ roleType.modified }}</div>

    </v-card-text>
    <v-card-actions>
      <v-btn :variant="'outlined'">Редактировать</v-btn>
      <v-btn :variant="'outlined'">Удалить</v-btn>
    </v-card-actions>
  </v-card>

</template>

<style scoped>

</style>