import { defineStore } from "pinia";
import { qRoles } from "@/apollo/queries"
import { apolloClient } from '@/apollo'
import { useAuth } from "./auth";


export const useRole = defineStore("role", () => {
    const uAuth = useAuth();

    const getAllRoles = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(qRoles(currentTokent.token))).data.roles
    }

    return {
        getAllRoles
    }
})