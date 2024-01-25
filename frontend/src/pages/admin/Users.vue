<script>

import userController from "@/controllers/UserController";
import Button from "@/components/Button.vue";

export default {
  components: {Button},

  data() {
    return {

      users: []

    }
  },

  mounted() {

    userController.getAll()
        .then(response => this.users = response.data)
        .catch(() => alert("Произошла ошибка при загрузке пользователей"))

  }

}

</script>

<template>

  <v-card :variant="'outlined'" v-for="user in users" :key="user.userId">

    <v-card-title>{{ user.fullName }}</v-card-title>
    <v-card-text>
      <div><strong>ID:</strong> {{ user.userId }}</div>
      <div><strong>Login:</strong>{{ user.login }}</div>
      <div><strong>Роли:</strong>{{user.roles.map(role=>role.name).join(', ')}}</div>
      <div><strong>Создан:</strong> {{ user.created }}</div>
      <div><strong>Изменен:</strong> {{ user.modified }}</div>

    </v-card-text>
    <v-card-actions>
      <v-btn :variant="'outlined'">Редактировать</v-btn>
      <v-btn :variant="'outlined'">Удалить</v-btn>
      <Button name="Тест" event="getAll()"></Button>
    </v-card-actions>
  </v-card>

</template>

<style scoped>

</style>