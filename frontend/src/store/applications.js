import { defineStore } from "pinia";
import { getApplications, getAllApplications } from "@/apollo/queries"
import { mAddApplication, mDeleteApplication } from "@/apollo/mutates"
import { apolloClient } from '@/apollo'
import { useAuth } from "./auth";


export const useApplication = defineStore("applications", () => {
    const uAuth = useAuth();

    const allApplications = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(getAllApplications(currentTokent.token))).data.applications
    }

    const getAllMyApplications = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(getApplications(currentTokent.token, localStorage.getItem("userId")))).data.applications
    }

    const addApplication = async (name, description, needRoles, teamId) => {
        const currentTokent = await uAuth.currentToken()
        const response = (await apolloClient.mutate(mAddApplication(currentTokent.token, name, description, localStorage.getItem("userId"), needRoles, teamId))).data.createApplication
        if (Object.keys(response).includes('messages')) {
            alert(`Произошла ошибка при создании заявки: ${response.messages[0].message}`)
            return null;
        }
        return response
    }    
    const deleteApplication = async (applicationId) => {
        const currentTokent = await uAuth.currentToken()
        const response = (await apolloClient.mutate(mDeleteApplication(currentTokent.token, applicationId))).data.deleteApplication
        if (Object.keys(response).includes('messages')) {
            alert(`Произошла ошибка при удалении заявки: ${response.messages[0].message}`)
            return null;
        }
        return response
    }        


    return {
        getAllMyApplications,
        addApplication,
        deleteApplication,
        allApplications
    }
})