<script>

import roleTypeController from "@/controllers/RoleTypeController";
import MyField from "@/components/MyField.vue";

export default {
  components: {MyField},

  data() {
    return {

      roleTypes: [],

      isEditRoleType : false,
      roleTypeEdit : {
        id : '',
        description : '',
      }
    }
  },

  mounted() {

    roleTypeController.getAll()
        .then(response => this.roleTypes = response.data)
        .catch(() => alert("Произошла ошибка при загрузке типов ролей"))

  },
  methods:{

    editRoleType(){
      this.roleTypes = this.roleTypes.filter( r => r.roleTypeId !== this.roleTypeEdit.id )
      roleTypeController.editRoleType(this.roleTypeEdit)
          .then(response => this.roleTypes.push(response.data))
          .catch(error => alert('Произошла ошибка при изменении типа роли' + error))

      this.isEditRoleType = false;
      this.roleTypeEdit.description = '';
    },

    showEditRoleTypeDialog(roleTypeId,roleTypeDescription){
      this.roleTypeEdit.id=roleTypeId;
      this.roleTypeEdit.description = roleTypeDescription
      this.isEditRoleType = true;
    },

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
      <v-btn @click="showEditRoleTypeDialog(roleType.id,roleType.description)" :variant="'outlined'">Редактировать</v-btn>
    </v-card-actions>
  </v-card>


  <v-dialog
      width="500"
      v-model="isEditRoleType"
  >
    <v-card title="Редактирование типа роли">

      <my-field label="Описание" :value="roleTypeEdit.description" v-on:update:modelValue="this.roleTypeEdit.description = $event" />

      <v-card-actions>
        <v-btn
            text="Сохранить"
            @click="editRoleType()"
        ></v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

</template>

<style scoped>

</style>