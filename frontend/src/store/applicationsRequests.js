import { defineStore } from "pinia";
import { applicationsRequests } from "@/apollo/queries"
import { mRejectApllicationEnterToTeam } from "@/apollo/mutates"
import { apolloClient } from '@/apollo'
import { useAuth } from "./auth";


export const useApplicationRequests = defineStore("applicationsRequests", () => {
    const uAuth = useAuth();

    const allApplications = async () => {
        const currentTokent = await uAuth.currentToken()
        return (await apolloClient.query(applicationsRequests(currentTokent.token, localStorage.getItem("userId")))).data.applicationsRequests
    }

    const rejectApllicationEnterToTeam = async (applicationId) => {
        const currentTokent = await uAuth.currentToken()
        const response = (await apolloClient.mutate(mRejectApllicationEnterToTeam(currentTokent.token, applicationId))).data.deleteApplicationEnterTeam
        if (Object.keys(response).includes('messages')) {
            alert(`Произошла ошибка при отклонении заявки: ${response.messages[0].message}`)
            return null;
        }
        return true
    }    


    return {
        allApplications,
        rejectApllicationEnterToTeam
    }
})