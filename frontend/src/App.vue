<template>
  <v-card>
    <v-layout >
      <v-navigation-drawer
          :width="350"
          expand-on-hover
          rail
      >

        <v-list density="compact" nav class="menu">

          <v-list-item
              value="13"
              title="Регистрация"
              @click=routeRegistration()
              prepend-icon="mdi-account-plus"></v-list-item>

          <v-list-item
              value="1"
              title="Мои команды"
              @click=routeMyTeams()
              prepend-icon="mdi-account-group"></v-list-item>

          <v-list-item
              value="2"
              title="Мои заявки"
              @click=routeMyApplication()
              prepend-icon="mdi mdi-human-edit"></v-list-item>

          <v-list-item
              value="3"
              title="Поиск команды"
              @click=routeSearchTeam()
              prepend-icon="mdi mdi-human-capacity-decrease"></v-list-item>

          <v-list-item
              value="4"
              title="Поиск участников команды"
              @click=routeSearchTeamMember()
              prepend-icon="mdi mdi-human-capacity-increase"></v-list-item>

          <v-list-item
              value="5"
              title="Все заявки"
              @click=routeAllApplication()
              prepend-icon="mdi mdi-account-search"></v-list-item>

          <v-list-item
              value="6"
              title="Все команды"
              @click=routeAllTeam()
              prepend-icon="mdi mdi-human-queue"></v-list-item>

          <v-list-item
              value="7"
              title="Сообщения"
              @click=routeMyDialog()
              prepend-icon="mdi mdi-chat"></v-list-item>



          <v-list-group value="10">
            <template v-slot:activator="{props}">
              <v-list-item
                  v-bind="props"
                  prepend-icon="mdi-cogs"
                  title="Администрирование"/>
            </template>

            <v-list-item
                value="10.1"
                title="Роли"
                @click=routeRoles()
                ></v-list-item>

            <v-list-item
                value="10.2"
                title="Список пользователей"
                @click=routeUsers()
                ></v-list-item>

            <v-list-item
                value="10.3"
                title="Список типов ролей"
                @click=routeRoleType()
                ></v-list-item>

            <v-list-item
                value="10.4"
                title="Список типов участников"
                @click=routeTeamMemberType()
                ></v-list-item>

            <v-list-item
                value="10.5"
                title="Список типов команд"
                @click=routeTypeTeam()
                ></v-list-item>

          </v-list-group>
        </v-list>

        <template v-slot:append>
           <account/>
        </template>


      </v-navigation-drawer>

      <v-main>
        <router-view></router-view>
      </v-main>

    </v-layout>

  </v-card>


</template>


<script>

import Account from "@/components/Account.vue";

export default {
  components: {Account},

  data() {
    return {

      open: ['Main'],

    }
  },

  methods: {

    routeRegistration() {

      this.$router.push('/registration')
    },

    routeMyTeams() {

      this.$router.push('/my-teams')
    },

    routeRoles() {

      this.$router.push('/roles')
    },

    routeMyApplication() {

      this.$router.push('/my-application')
    },

    routeSearchTeam() {

      this.$router.push('/search-team')
    },

    routeSearchTeamMember() {

      this.$router.push('/search-team-member')
    },

    routeAllApplication() {

      this.$router.push('/all-application')
    },

    routeAllTeam() {

      this.$router.push('/all-team')
    },

    routeRoleType() {

      this.$router.push('/role-type')
    },

    routeTeamMemberType() {

      this.$router.push('/team-member-type')
    },

    routeTypeTeam() {

      this.$router.push('/type-team')
    },

    routeUsers() {

      this.$router.push('/users')
    },

    routeMyDialog() {

      this.$router.push('/dialog')
    },



  }

}

</script>

<style>


.menu{
  display: flex;
  flex-direction: column;
}
</style>