import {createRouter, createWebHistory} from "vue-router";
import Main from "@/pages/Main.vue";
import MyTeams from "@/pages/MyTeams.vue";
import Roles from "@/pages/admin/Roles.vue";
import Login from "@/pages/Login.vue";
import MyApplication from "@/pages/MyApplication.vue";
import SearchTeam from "@/pages/SearchTeam.vue";
import SearchTeamMember from "@/pages/SearchTeamMember.vue";
import AllApplication from "@/pages/admin/AllApplication.vue";
import AllTeam from "@/pages/admin/AllTeam.vue";
import RoleType from "@/pages/admin/RoleType.vue";
import TeamMemberType from "@/pages/admin/TeamMemberType.vue";
import TypeTeam from "@/pages/admin/TypeTeam.vue";
import Users from "@/pages/admin/Users.vue";
import Dialog from "@/pages/Dialog.vue";
import Registration from "@/pages/Registration.vue";

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

    {
        path: '/login',
        component : Login
    },

    {
        path: '/registration',
        component : Registration
    },

    {
        path: '/my-application',
        component : MyApplication
    },

    {
        path: '/search-team',
        component : SearchTeam
    },

    {
        path: '/search-team-member',
        component : SearchTeamMember
    },

    {
        path: '/all-application',
        component : AllApplication
    },

    {
        path: '/all-team',
        component : AllTeam
    },

    {
        path: '/role-type',
        component : RoleType
    },

    {
        path: '/team-member-type',
        component : TeamMemberType
    },

    {
        path: '/type-team',
        component : TypeTeam
    },

    {
        path: '/users',
        component : Users
    },

    {
        path: '/dialog',
        component : Dialog
    },



]


const router = createRouter({
    routes,
    history : createWebHistory(process.env.BASE_URL)
})


export default router