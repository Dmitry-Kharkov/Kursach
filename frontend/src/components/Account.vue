<script>

import MyField from "@/components/MyField.vue";
import userController from "@/controllers/UserController";

export default {
  components: {MyField},
  data(){
    return{
      isEditRole:false,
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
    editPassword(){
      if(this.oldPassword===this.testPassword) {
        userController.editPassword(this.user.password)
            .then(response => this.user = response.data)
            .catch(() => alert("Произошла ошибка при загрузке ролей"))
      }
      else{
        alert("Пароли не совпадают!")
      }
    },
    showEditPasswordDialog(){
      this.oldPassword=this.user.password;
      this.isEditRole = true;
    },
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
              @click="showEditPasswordDialog()"
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
                  @click="console.log(this.user.fullName,this.user.email)"
                  rounded
                  variant="text"
              >
                Edit Account
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
      v-model="isEditRole"
  >
    <v-card title="Изменение пароля">

      <my-field label="Старый пароль" :value="''" v-on:update:modelValue="this.user.testPassword = $event"/>
      <my-field label="Новый пароль" :value="''" v-on:update:modelValue="this.user.password = $event" />

      <v-card-actions>
        <v-btn
            text="Сохранить"
            @click="editPassword()"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>


</template>

<style>

</style>