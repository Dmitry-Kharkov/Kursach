<script>

import roleController from "@/controllers/RoleController";
import {role} from "vuetify/locale";

export default {

  data() {
    return {

      roles: []

    }
  },

  mounted() {

    roleController.getAll()
        .then(response => this.roles = response.data)
        .catch(() => alert("Произошла ошибка при загрузке ролей"))

    roleController.deleteRoleById("roleId")
        .catch(() => alert("Произошла ошибка при удалении роли"))


  }


}

</script>

<template>

  <v-card :variant="'outlined'" v-for="role in roles" :key="role.roleId">

    <v-card-title>{{ role.name }}</v-card-title>
    <v-card-text>
      <div><strong>ID:</strong> {{ role.roleId }}</div>
      <div>{{ role.description }}</div>
      <div><strong>Создан:</strong> {{ role.created }}</div>
      <div><strong>Изменен:</strong> {{ role.modified }}</div>

    </v-card-text>
    <v-card-actions>
      <v-btn :variant="'outlined'">Редактировать</v-btn>
      <v-btn onclick="deleteRoleById(role.roleId)" :variant="'outlined'">Удалить</v-btn>
    </v-card-actions>
  </v-card>

</template>

<style>

</style>