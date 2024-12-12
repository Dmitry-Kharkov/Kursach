<script setup>
import PopupComponent from '@/components/PopupComponent.vue'
import { defineComponent } from 'vue';
import {useApplication} from '@/store/applications';
import { DateTime } from 'luxon';
import { useRole } from '@/store/roles';
import { useTeam } from '@/store/team';
</script>

<template>
    <div class="header">
        <h1>Мои заявка</h1>
        <v-btn @click="showAdd = true">Добавить заявку</v-btn>
    </div>
    <v-data-table
        :headers="headers"
        :items="items"
        :items-per-page="5"
        class="elevation-1"
    >
        <template #item.completed="{ item }">
            <v-chip
                :color="item.completed ? 'success' : 'error'"
                text-color="white"
            >
                {{ item.completed ? 'Завершено' : 'Не завершено' }}
            </v-chip>
        </template>
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
            <v-btn @click="deleteApplication(item.id)">Удалить</v-btn>
        </template>
    </v-data-table>

    <PopupComponent title="Новая заявка" v-if="showAdd" :showButtonClose="true" @close="showAdd = false">
        <template #content>
            <v-select
                :items="teamNames"
                v-model="selectedTeamName"
                label="Для какой команды" />
            <hr />            
            <v-text-field v-model="name" label="Название"></v-text-field>
            <v-text-field v-model="description" label="Описание"></v-text-field>
            <v-select
                v-if="roleNames.length > 0"
                :items="roleNames"
                v-model="selectedRoles"
                multiple
                label="Требуются" />
            <hr />
            <div class="header">
                <v-btn @click="cancelAddMyNewApplication" >Отменить</v-btn>
                <v-btn @click="addMyNewApplication" v-if="showSaveButton" >Добавить</v-btn>
            </div>
        </template>
    </PopupComponent>

</template>

<style scoped>

</style>

<script>

export default defineComponent({
    name: "MyApplication",
    components: {
        PopupComponent
    },
    data() {
        return {
            showAdd: false,
            uApplication: useApplication(),
            uRoles: useRole(),
            uTeams: useTeam(),
            roles: [],
            items: [],
            name: "",
            myTeams: [],
            description: "",
            selectedRoles: [],
            selectedTeamName: "",
            headers: [
                {
                    title: 'ID',
                    value: 'id',
                },
                {
                    title: "Команда",
                    value: "team"
                },                
                {
                    title: "Название",
                    value: "name"
                },
                {
                    title: "Описание",
                    value: "description"
                },
                {
                    title: "Требуется",
                    value: "needRoles"
                },
                {
                    title: "Создано",
                    value: "createdAt"
                },
                {
                    title: "Завершено",
                    value: "completed"
                },
                {
                    title: "Действия",
                    value: "actions"
                },                
            ]
        }
    },
    methods: {
        clearAllData() {
            this.name = "";
            this.description = "";
            this.selectedRoles = [];
            this.selectedTeamName = "";
            this.showAdd = false
        },
        async addMyNewApplication() {
            const item = await this.uApplication.addApplication(this.name, this.description, this.roleValuesFromSelected, this.teamIdBuName)
            if (item != null) {
                this.items.push(this.fromResponseToItem(item))
            }
            this.clearAllData()
        },
        async deleteApplication(applicationId) {
            const res = await this.uApplication.deleteApplication(applicationId)
            if (res) {
                this.items = this.items.filter((item) => item.id !== applicationId)
            }
        },
        cancelAddMyNewApplication() {
            this.clearAllData()
        },
        fromResponseToItem(item) {
            return {
                id: item.id,
                team: item.team.name,
                name: item.name,
                description: item.description,
                createdAt: DateTime.fromISO(item.createdAt).toLocaleString(DateTime.DATETIME_MED),
                completed: item.completed,
                needRoles: item.needRoles
            }
        }        
    },
    computed: {
        selectedTeamObject() {
            return this.myTeams.find(team => team.name == this.selectedTeamName)
        },
        roleNames() {
            return this.selectedTeamObject != undefined ? this.selectedTeamObject.needRoles.map((role) => role.name) : []
        },
        roleValuesFromSelected() {
            return this.selectedTeamObject != undefined ?  this.selectedTeamObject.needRoles.filter((role) => this.selectedRoles.includes(role.name)).map((role) => role.id) : []
        },
        showSaveButton() {
            return this.name.length > 0 && this.description.length > 0 && this.selectedRoles.length > 0
        },
        teamNames() {
            return this.myTeams.map((team) => team.name)
        },
        teamIdBuName() {
            return this.myTeams.find((team) => team.name === this.selectedTeamName).id
        }
    },
   async mounted() {
        this.items = (await this.uApplication.getAllMyApplications()).map((item) => {
            return this.fromResponseToItem(item)
        })
        this.myTeams = await this.uTeams.myTeamsShort()
    }
})
</script>