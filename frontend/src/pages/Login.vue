<template>
  <v-label v-text="text"></v-label>
  <v-sheet class="bg-deep-purple pa-15" location=""  rounded>
    <v-card class="mx-auto px-6 py-8" max-width="450">
      <v-form
          v-model="form"
          @submit.prevent="onSubmit"
      >
        <v-text-field
            v-model="login"
            :readonly="loading"
            :rules="[required]"
            class="mb-2"
            clearable
            label="Логин"
        ></v-text-field>



        <v-text-field
              v-model="password"
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
            :disabled="!form"
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

import UserController from "@/controllers/UserController";
import { useAuth } from "@/store/auth";

export default {
  data: () => ({
    isExists:true,
    uAuth: useAuth(),

    text:"Пользователь не найден",
    min: v => v.length >= 0 || 'Минимум 8 символов',
    show: false,
    form: false,
    login: null,
    password: null,
    loading: false,
  }),

  methods: {
    async onSubmit () {
      const res = await this.uAuth.auth(this.login, this.password)
      if (res != null) {
        this.$router.push('/my-application');
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