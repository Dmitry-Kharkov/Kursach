<script>

import userController from "@/controllers/UserController";
import Button from "@/components/Button.vue";
import { useRole } from "@/store/roles";
import PopupComponent from "@/components/PopupComponent.vue";
import { userUser } from "@/store/user";

export default {
  components: {Button, PopupComponent},

  data() {
    return {

      users: [],
      uRole: useRole(),
      uUser: userUser(),
      selectedUser: null,
      selectedRole: null,
      search: {
        searchValue:"t",
        count:'5',
        coefficient:'0',
        from:'0'

      }
    }


  },
  computed: {
    roles() {
      return this.uRole.roles.map(role => {
        return {
          value: role.roleId,
          text: role.name
        }
      })
    }
  },
  async mounted() {
    await this.uRole.loadAllRoles();
    userController.getAll()
        .then(response => this.users = response.data)
        .catch(() => alert("Произошла ошибка при загрузке пользователей"))

  },

  methods: {
    searchUsers(search) {
      userController.searchUsers(search)
          .then(response => this.users = response.data)
          .catch(() => alert("Произошла ошибка при поиске пользователей"))
      this.search.coefficient+=1
      this.search.from=this.search.count*this.search.coefficient+1
    },
    async saveUpdate() {
      await this.uUser.updateRole(this.roles.find(role => role.text === this.selectedRole).value)
      console.log(this.roles.find(role => role.text === this.selectedRole).value)
      this.selectedUser = null
      this.selectedRole = null
    }
  }


}

</script>

<template>
  <Button name="Поиск пользователей" @click="searchUsers(search)" :location="'top center'"></Button>

  
  <v-card :variant="'outlined'" v-for="user in users" :key="user.userId">

    <v-card-title>{{ user.fullName }}</v-card-title>
    <v-card-text>
      <div><strong>ID:</strong> {{ user.userId }}</div>
      <div><strong>Login:</strong>{{ user.login }}</div>
      <div><strong>Email:</strong>{{ user.email }}</div>
      <div><strong>Роли:</strong>{{user.roles.map(role=>role.name).join(', ')}}</div>
      <div><strong>Создан:</strong> {{ user.created }}</div>
      <div><strong>Изменен:</strong> {{ user.modified }}</div>

    </v-card-text>
    <v-card-actions>
      <v-btn :variant="'outlined'" @click="selectedUser = user.userId">Редактировать</v-btn>
      <v-btn :variant="'outlined'">Удалить</v-btn>
    </v-card-actions>
  </v-card>
  <PopupComponent v-if="selectedUser != null" title="Редактировать пользователя" :show-button-close="true" @close="selectedUser=null">
    <template #content>
      <v-select :items="roles.map(role => role.text)" v-model="selectedRole" label="Роль"></v-select>
      <hr />
      <v-btn :variant="'outlined'" @click="saveUpdate">Сохранить</v-btn>
    </template>
  </PopupComponent>
</template>

<style scoped>

</style>