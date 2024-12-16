import { defineStore } from "pinia";
import { qRoles, qGetRoleCategories } from "@/apollo/queries"
import { apolloClient } from '@/apollo'
import { useAuth } from "./auth";


export const useRole = defineStore("role", () => {
    const uAuth = useAuth();

    const getAllRoles = async (categoryId) => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(qRoles(currentTokent.token, categoryId))).data.roles
    }

    const getRoleCategories = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(qGetRoleCategories(currentTokent.token))).data.roleCategories
    }

    return {
        getAllRoles,
        getRoleCategories
    }
})