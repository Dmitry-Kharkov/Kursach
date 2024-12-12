import { defineStore } from "pinia";
import { getMyTeams, getMyTeamsShort, getAllTeams, getAllTeamsName } from "@/apollo/queries"
import { enterToTeam, mCreateTeam, mDeleteTeam, mApplicationEnterToTeam } from "@/apollo/mutates"
import { apolloClient } from '@/apollo'
import { useAuth } from "./auth";


export const useTeam = defineStore("team", () => {
    const uAuth = useAuth();

    const getAllMyTeams = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(getMyTeams(currentTokent.token, localStorage.getItem("userId")))).data.teams
    }

    const allTeams = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(getAllTeams(currentTokent.token))).data.teams
    }
    const allTeamsName = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(getAllTeamsName(currentTokent.token))).data.teams
    }    


    
    const myTeamsShort = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(getMyTeamsShort(currentTokent.token, localStorage.getItem("userId")))).data.teams
    }    
    

    const createTeam = async (name, description) => {
        const currentTokent = await uAuth.currentToken()
        const res = (await apolloClient.mutate(mCreateTeam(currentTokent.token, name, description, localStorage.getItem("userId")))).data.createTeam
        if (Object.keys(res).includes('messages')) {
            alert(`Произошла ошибка при вступлении в команду: ${res.messages[0].message}`)
            return null;
        }
        return res
    }    


    const enter = async (teamId, userId, roleId) => {
        const currentTokent = await uAuth.currentToken()
        const res = (await apolloClient.mutate(enterToTeam(currentTokent.token, teamId, userId, roleId))).data.createTeamMember
        if (Object.keys(res).includes('messages')) {
            alert(`Произошла ошибка при вступлении в команду: ${res.messages[0].message}`)
            return null;
        }
        return res
    }    

    const deleteTeam = async (teamId) => {
        const currentTokent = await uAuth.currentToken()
        const res = (await apolloClient.mutate(mDeleteTeam(currentTokent.token, teamId, ))).data.deleteTeam
        if (Object.keys(res).includes('messages')) {
            alert(`Произошла ошибка при удалении команды: ${res.messages[0].message}`)
            return null;
        }
        return res
    }    
    const applicationEnterToTeam = async (description, applicationId, roleId) => {
        const currentTokent = await uAuth.currentToken()
        const res = (await apolloClient.mutate(mApplicationEnterToTeam(currentTokent.token, description, applicationId, localStorage.getItem("userId"), roleId))).data.createApplicationEnterTeam
        if (Object.keys(res).includes('messages')) {
            alert(`Произошла ошибка при вступления в команду: ${res.messages[0].message}`)
            return null;
        }
        return res
    }        

    
    return {
        getAllMyTeams,
        createTeam,
        enter,
        deleteTeam,
        myTeamsShort,
        allTeams,
        applicationEnterToTeam,
        allTeamsName
    }
})