import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/router'
import '@/assets/main.css';
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css'
import { createPinia } from 'pinia';
import { apolloProvider } from "@/apollo/index"

const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
    }
})

createApp(App).use(router).use(createPinia()).use(vuetify).mount('#app')
