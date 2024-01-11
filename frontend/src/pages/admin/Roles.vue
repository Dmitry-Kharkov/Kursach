<script>

import roleController from "@/controllers/RoleController";
import Fab from "@/components/Fab.vue";
import MyField from "@/components/MyField.vue";
import roleTypeController from "@/controllers/RoleTypeController";

export default {
  components: {MyField, Fab},

  data() {
    return {

      roles: [],
      isAddRole : false,
      role : {
        id : '',
        name: '',
        description : '',
        roleTypeId : ''
      },

      isEditRole : false,
      roleEdit : {
        id : '',
        name: '',
        description : '',
        roleTypeId : ''
      },

      roleTypes : [],
      selectRoleType : {}

    }
  },

  mounted() {

    roleController.getAll()
        .then(response => this.roles = response.data)
        .catch(() => alert("Произошла ошибка при загрузке ролей"))

    roleTypeController.getAll()
        .then(response => this.roleTypes = response.data)
        .catch(() => alert("Произошла ошибка при загрузке типов ролей"))

  },

  methods: {


    deleteRoleById(id){

      roleController.deleteRoleById(id)
          .catch(() => alert("Произошла ошибка при удалении роли"))

      this.roles = this.roles.filter( r => r.roleId !== id )

    },

    addNewRole(){

      this.role.roleType = this.selectRoleType.name
      console.log(this.selectRoleType)
      roleController.addRole(this.role)
          .then(response => this.roles.push(response.data))
          .catch(error => alert('Произошла ошибка при добавлении роли' + error))

      this.isAddRole = false;

      this.role.name = '';
      this.role.description = '';

    },

    showAddNewRoleDialog(){
      this.selectRoleType = {};
      this.isAddRole = true;
    },

    editRole(){
      this.roles = this.roles.filter( r => r.roleId !== this.roleEdit.id )
      roleController.editRole(this.roleEdit)
          .then(response => this.roles.push(response.data))
          .catch(error => alert('Произошла ошибка при изменении роли' + error))

      this.isEditRole = false;
      this.roleEdit.name = '';
      this.roleEdit.description = '';
    },

    showEditRoleDialog(role){
      this.roleEdit.id=role.id;
      this.roleEdit.name = role.name
      this.roleEdit.description = role.description
      this.selectRoleType = this.roleTypes.filter( type => type.name === role.roleType)
      this.isEditRole = true;
    },

    itemProps(item){
      return {
        title: item.name,
        subtitle: item.description,
        id : item.roleTypeId
      }

    }
  },




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
      <v-btn @click="showEditRoleDialog(role)" :variant="'outlined'">Редактировать</v-btn>
      <v-btn @click="deleteRoleById(role.roleId)" :variant="'outlined'">Удалить</v-btn>
    </v-card-actions>
  </v-card>


  <v-dialog
      width="500"
      v-model="isAddRole"
  >
      <v-card title="Добавление новой роли">

        <my-field label="Название" :value="role.name" v-on:update:modelValue="this.role.name = $event"/>
        <my-field label="Описание" :value="role.description" v-on:update:modelValue="this.role.description = $event" />
        <v-select
            variant="outlined"
            class = "select"
            label="Тип роли"
            v-model="selectRoleType"
            :items="roleTypes"
            :item-props="itemProps"
        ></v-select>

        <v-card-actions>
          <v-btn
              text="Сохранить"
              @click="addNewRole()"
          ></v-btn>
        </v-card-actions>
      </v-card>
  </v-dialog>


  <v-dialog
      width="500"
      v-model="isEditRole"
  >
    <v-card title="Редактирование роли">

      <my-field label="Название" :value="roleEdit.name" v-on:update:modelValue="this.roleEdit.name = $event"/>
      <my-field label="Описание" :value="roleEdit.description" v-on:update:modelValue="this.roleEdit.description = $event" />
      <v-select
          variant="outlined"
          class = "select"
          label="Тип роли"
          :multiple=false
          v-model="selectRoleType"
          :items="roleTypes"
          :item-props="itemProps"
      ></v-select>

      <v-card-actions>
        <v-btn
            text="Сохранить"
            @click="editRole()"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>


  <fab v-on:fab_action = "showAddNewRoleDialog()" ></fab>
</template>

<style>
.select{
  margin: 25px;
}
</style>