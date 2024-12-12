import { defineStore } from "pinia";
import { getMyRoles, qGetAllUsers } from "@/apollo/queries"
import { mUpdatePassword, mUpdateRoles } from "@/apollo/mutates"
import { apolloClient } from '@/apollo'
import { useAuth } from "./auth";


export const userUser = defineStore("user", () => {
    
    const uAuth = useAuth();

    const myRoles = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(getMyRoles(currentTokent.token, ))).data.me.roles
    }

    const getAllUsers = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(qGetAllUsers(currentTokent.token, localStorage.getItem("userId")))).data.users
    }    

    const updateRoles = async (roles) => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.mutate(mUpdateRoles(currentTokent.token, localStorage.getItem("userId"), roles))).data.updateAccount.roles
    }        

    const updatePassword = async (oldPassword, newPassword) => {
        const currentTokent = await uAuth.currentToken()
        const responce = (await apolloClient.mutate(mUpdatePassword(currentTokent.token, oldPassword, newPassword)))
        if (responce.data == null) {
            alert(responce.errors[0].message)
            return
        }
        await uAuth.auth(responce.data.updatePassword.username, newPassword)
        return true
    }    

    return {
        myRoles, getAllUsers, updatePassword, updateRoles
    }
})