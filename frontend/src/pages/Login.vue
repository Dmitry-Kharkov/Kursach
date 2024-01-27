<template>
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
import Account from "@/components/Account.vue";

export default {
  data: () => ({
    min: v => v.length >= 8 || 'Минимум 8 символов',
    show: false,
    form: false,
    login: null,
    password: null,
    loading: false,
  }),

  methods: {
    onSubmit () {
      if (!this.form) return

      this.loading = true
      console.log(this.login,this.password)
      Account.data().user.fullName=this.login
      Account.data().user.email=this.password
      setTimeout(() => (this.loading = false), 2000)
    },
    required (v) {
      return !!v || 'Поле обязательно для заполнения'
    },
  },
}
</script>



<!--<script>-->
<!--export default {-->
<!--  data () {-->
<!--    return {-->
<!--      show: false,-->
<!--      password: '',-->
<!--      rules: {-->
<!--        required: value => !!value || '',-->
<!--        min: v => v.length >= 8 || 'Минимум 8 символов',-->
<!--        loginMatch: () => (`Введенные вами логин и пароль не совпадают`),-->
<!--      },-->
<!--    }-->
<!--  },-->
<!--}-->
<!--</script>-->


<!--<template>-->


<!--    <v-responsive-->
<!--        class="mx-auto"-->
<!--        max-width="344"-->
<!--    >-->
<!--      <v-text-field-->
<!--          label="Логин"-->
<!--          hide-details="auto"-->
<!--      ></v-text-field>-->
<!--    </v-responsive>-->

<!--  <v-form>-->
<!--    <v-container fluid>-->
<!--      <v-row>-->
<!--        <v-col-->
<!--            cols="12"-->
<!--            sm="6"-->
<!--        >-->
<!--          <v-text-field-->
<!--              v-model="password"-->
<!--              :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"-->
<!--              :rules="[rules.required, rules.min]"-->
<!--              :type="show ? 'text' : 'password'"-->
<!--              name="input-10-1"-->
<!--              label="Пароль"-->
<!--              hint="Не менее 8 символов"-->
<!--              counter-->
<!--              @click:append="show = !show"-->
<!--          ></v-text-field>-->
<!--        </v-col>-->

<!--      </v-row>-->
<!--    </v-container>-->
<!--  </v-form>-->
<!--</template>-->



<style scoped>

</style>