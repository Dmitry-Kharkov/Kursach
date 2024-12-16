<script setup>
import { defineComponent } from 'vue';
import { DateTime } from 'luxon';
import { useApplicationRequests } from '@/store/applicationsRequests';
import { useTeam } from '@/store/team';
</script>

<template>
    <div class="header">
        <h1>Запросы на вступление</h1>
    </div>
    <v-data-table
        :headers="headers"
        :items="items"
        :items-per-page="5"
        class="elevation-1"
    >
        <template #item.needRoles="{ item }">
            <v-chip
                v-for="role in item.needRoles"
                :key="role"
                :color="role === 'developer' ? 'primary' : 'secondary'"
                text-color="white"
                class="mr-2"
            >
                {{ role.name }}
            </v-chip>
        </template>
        <template #item.actions="{ item }">
            <v-btn @click="rejectApplication(item.id)">Отклонить</v-btn>
            <v-btn @click="enterToTeam(item.id, item.teamId, item.userId, item.roleId)" >Принять</v-btn>
        </template>
    </v-data-table>
</template>

<style scoped>

</style>

<script>

export default defineComponent({
    name: "ApplicationRequests",
    data() {
        return {
            uApplicationRequest: useApplicationRequests(),
            uTeam: useTeam(),
            items: [],
    
            headers: [
                {
                    title: 'ID',
                    value: 'id',
                },
                {
                    title: "заявка",
                    value: "applicationName"
                },                
                {
                    title: "Пользователь",
                    value: "name"
                },
                {
                    title: "Роль",
                    value: "role"
                },
                {
                    title: "Комментарий",
                    value: "description"
                },
                {
                    title: "Дата подачи",
                    value: "createdAt"
                },
                {
                    title: "Действия",
                    value: "actions"
                }
            ]
        }
    },
    methods: {

        fromResponseToItem(item) {
            return {
                id: item.id,
                applicationName: item.application.name,
                name: item.user.username,
                userId: item.user.id,
                description: item.description,
                createdAt: DateTime.fromISO(item.createdAt).toLocaleString(DateTime.DATETIME_MED),
                role: item.role.name,
                teamId: item.application.team.id,
                roleId: item.role.id
            }
        },
        async enterToTeam(applicationId, teamId, userId, roleId) {
            const res = this.uTeam.enter(teamId, userId, roleId)
            if (res) {
                this.items = this.items.filter((item) => item.id !== applicationId)
            }
        },
        async rejectApplication(applicationId) {
            const res = this.uApplicationRequest.rejectApllicationEnterToTeam(applicationId)
            if (res) {
                this.items = this.items.filter((item) => item.id !== applicationId)
            }
        },
        async load() {
            this.items = (await this.uApplicationRequest.allApplications()).map((item) => {
                return this.fromResponseToItem(item)
            })            
        }
    },
   async mounted() {
        await this.load()
    }
})
</script>