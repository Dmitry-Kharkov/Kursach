<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer
          :width="350"
          expand-on-hover
          rail
      >

        <v-list density="compact" nav>

          <v-list-item
              value="1"
              title="Мои команды"
              @click=routeMyTeams()
              prepend-icon="mdi-account-group"></v-list-item>

          <v-list-group value="5">
            <template v-slot:activator="{props}">
              <v-list-item
                  v-bind="props"
                  prepend-icon="mdi-cogs"
                  title="Администрирование"/>
            </template>

            <v-list-item
                value="5.1"
                title="Роли"
                @click=routeRoles()
                prepend-icon="mdi-account-group"></v-list-item>

          </v-list-group>

        </v-list>
      </v-navigation-drawer>

      <v-main>
        <router-view></router-view>
      </v-main>
    </v-layout>
  </v-card>

</template>


<script>

export default {

  data() {
    return {

      open: ['Main']


    }
  },

  methods: {

    routeMyTeams() {

      this.$router.push('/my-teams')
    },

    routeRoles() {

      this.$router.push('/roles')
    }


  }

}

</script>

<style>

</style>