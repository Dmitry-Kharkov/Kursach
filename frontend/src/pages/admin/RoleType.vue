<script>

import roleTypeController from "@/controllers/RoleTypeController";
import ItemViewer from "@/components/ItemViewer.vue";

export default {
  components: {ItemViewer},

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

  <item-viewer
      :id="roleType.roleTypeId"
      :item="roleType"
      v-for="roleType in roleTypes" :key="roleType.roleTypeId"
  />

</template>

<style scoped>

</style>