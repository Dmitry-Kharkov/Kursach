<template>
  <v-sheet class="bg-deep-purple pa-15" location=""  rounded>
    <v-card class="mx-auto px-6 py-8" max-width="450">
      <v-form
          v-model="form"
          @submit.prevent="onSubmit"
      >
        <v-text-field
            v-model="user.login"
            :readonly="loading"
            :rules="[required]"
            class="mb-2"
            clearable
            label="Логин"
        ></v-text-field>

        <v-text-field
            v-model="user.password"
            :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[required, min]"
            :type="show ? 'text' : 'password'"
            name="input-10-1"
            label="Пароль"
            hint="Не менее 8 символов"
            counter
            @click:append="show = !show"
        ></v-text-field>

        <v-btn
            :disabled="!enableButtonSave"
            :loading="loading"
            block
            color="success"
            size="large"
            type="submit"
            variant="elevated"
        >
          Вход
        </v-btn>
      </v-form>
    </v-card>
  </v-sheet>
</template>
<script>
import { useAuth } from "@/store/auth";

export default {
  data: () => ({
    uAuth: useAuth(),
    min: v => v.length >= 8 || 'Минимум 8 символов',
    show: false,
    form: false,
    name: null,
    user:{
      login: null,
      password: null,
      loading: false,
    }
  }),
  computed: {
    enableButtonSave () {
      return this.user.login && this.user.password
    }
  },
  methods: {
    async onSubmit () {
      const token = await this.uAuth.register(this.user.login, this.user.password)
      if(token != null) {
        this.$router.push("/my-application")
      }
    },
    required (v) {
      return !!v || 'Поле обязательно для заполнения'
    },
  },
}
</script>
<style scoped>

</style>
