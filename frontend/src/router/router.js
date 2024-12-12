import {createRouter, createWebHistory} from "vue-router";
import Main from "@/pages/Main.vue";
import MyTeams from "@/pages/MyTeams.vue";
import Login from "@/pages/Login.vue";
import MyApplication from "@/pages/MyApplication.vue";
import SearchTeam from "@/pages/SearchTeam.vue";
import SearchTeamMember from "@/pages/SearchTeamMember.vue";
import Dialog from "@/pages/Dialog.vue";
import Registration from "@/pages/Registration.vue";
import { useAuth } from "@/store/auth";

const routes = [

    {
        path: '/',
        component : Main,
        meta: { auth: true, },
    },


    {
        path: '/my-teams',
        component : MyTeams,
        meta: { auth: true, },
    },

    {
        path: '/login',
        component : Login,
        meta: { auth: false, },
    },

    {
        path: '/registration',
        component : Registration,
        meta: { auth: false, },
    },

    {
        path: '/my-application',
        component : MyApplication,
        meta: { auth: true, },
    },
    {
        path: '/all-application',
        component: () => import('@/pages/AllApplication.vue'),
        meta: { auth: true, },
    },    
    {
        path: '/applications/requests',
        component: () => import('@/pages/ApplicationRequests.vue'),
        meta: { auth: true, },
    },        

    {
        path: '/search-team',
        component : SearchTeam,
        meta: { auth: true, },
    },

    {
        path: '/search-team-member',
        component : SearchTeamMember,
        meta: { auth: true, },
    },

    {
        path: '/dialog',
        component : Dialog,
        meta: { auth: true, },
    },



]


const router = createRouter({
    routes,
    history : createWebHistory(process.env.BASE_URL)
})


router.beforeEach(async (to, from, next) => {
    const uStorage = useAuth()
    const isValid = await uStorage.currentToken() != null
    const requireAuth = to.matched.some(record => record.meta.auth)
    if (requireAuth && !isValid) {
      localStorage.clear()
      next('/login')
    } else {
      next()
    }
  })

export default router