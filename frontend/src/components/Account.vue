<script>

import MyField from "@/components/MyField.vue";
import userController from "@/controllers/UserController";
import { useAuth } from "@/store/auth";
import { useRole } from "@/store/roles";
import { userUser } from "@/store/user";

export default {
  components: {MyField},
  data(){
    return{
      uUser: userUser(),
      uRoles: useRole(),
      roles: [],
      selectedRoles: [],
      myRoles: [],
      uUser: userUser(),
      editRole: false,
      editPassword: false,
      user: {
        initials: 'DH',
        fullName: 'Dima Harkov',
        login:'test',
        email: 'harkov.dima2005@gmail.com',
        password: 'test'
      },
      oldPassword:'',
      testPassword:''
    }
  },
  methods:{
    async updateRoles(){
      this.myRoles = await this.uUser.updateRoles(this.selectedRolesId)
      this.selectedRoles = this.myRoles.map(role => role.name);
      this.editRole = false;
    },
    async updatePassword(){
      if(await this.uUser.updatePassword(this.oldPassword,this.user.password)) {
        alert('Пароль успешно изменен');
      }
    },
  },
  computed: {
    rolesName(){
      return this.roles.map(role => role.name);
    },
    selectedRolesId(){
      return this.roles.filter(role => this.selectedRoles.includes(role.name)).map(role => role.id);
    }
  },
  async mounted(){
    this.roles = await this.uRoles.getAllRoles();
    this.myRoles = await this.uUser.myRoles();
    this.selectedRoles = this.myRoles.map(role => role.name);
  }
}

</script>

<template>
  <v-container>
    <v-row justify="start"
    >
      <v-menu
          rounded
      >
        <template v-slot:activator="{ props }">
          <v-btn
              icon
              v-bind="props"
          >
            <v-avatar
                color="brown"
                size="large"
            >
              <span class="text-h5">{{ user.initials }}</span>
            </v-avatar>
          </v-btn>
        </template>
        <v-card>
          <v-card-text>
            <div class="mx-auto text-center">
              <v-avatar
                  color="brown"
              >
                <span class="text-h5">{{ user.initials }}</span>
              </v-avatar>
              <h3>{{ user.fullName }}</h3>
              <p class="text-caption mt-1">
                {{ user.email }}
              </p>
              <v-divider class="my-3"></v-divider>
              <v-btn
                  @click="editRole = true"
                  rounded
                  variant="text"
              >
                Edit Account
              </v-btn>
              <v-btn
                  @click="editPassword = true"
                  rounded
                  variant="text"
              >
                Edit password
              </v-btn>              
              <v-divider class="my-3"></v-divider>
              <v-btn
                  rounded
                  variant="text"
              >
                Disconnect
              </v-btn>
            </div>
          </v-card-text>
        </v-card>
      </v-menu>
    </v-row>
  </v-container>

  <v-dialog
      width="500"
      v-model="editRole"
  >
    <v-card
        title="Изменение роли"
    >
      <v-select
          v-model="selectedRoles"
          :items="rolesName"
          label="Роли"
          multiple
      ></v-select>
      <v-btn
            text="Сохранить"
            @click="updateRoles()"
        ></v-btn>      
    </v-card>
  </v-dialog>

  <v-dialog
      width="500"
      v-model="editPassword"
  >
    <v-card title="Изменение пароля">

      <my-field label="Старый пароль" :value="''" v-on:update:modelValue="this.oldPassword = $event"/>
      <my-field label="Новый пароль" :value="''" v-on:update:modelValue="this.user.password = $event" />

      <v-card-actions>
        <v-btn
            text="Сохранить"
            @click="updatePassword()"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>


</template>

<style>

</style>