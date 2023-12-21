import {createRouter, createWebHistory} from "vue-router";
import Main from "@/pages/Main.vue";
import MyTeams from "@/pages/MyTeams.vue";
import Roles from "@/pages/admin/Roles.vue";

const routes = [

    {
        path: '/',
        component : Main
    },


    {
        path: '/my-teams',
        component : MyTeams
    },

    {
        path: '/roles',
        component : Roles
    },



]


const router = createRouter({
    routes,
    history : createWebHistory(process.env.BASE_URL)
})


export default router