<script setup>
import PopupComponent from '@/components/PopupComponent.vue'
import { defineComponent } from 'vue';
import {useApplication} from '@/store/applications';
import { useTeam } from '@/store/team';
import { DateTime } from 'luxon';
import { userUser } from '@/store/user';
</script>

<template>
    <div class="header">
        <h1>Мои команды</h1>
        <v-btn @click="showAdd = true">Добавить команду</v-btn>
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
        <template #item.actions="{ item }">
            <v-btn @click="deleteTeam(item.id)">Удалить</v-btn>
        </template>        
        <template #item.members="{ item }">
            <v-chip
                v-for="member in item.members"
                :key="member.id"
                :color="member.role === 'developer' ? 'primary' : 'secondary'"
                text-color="white"
                class="mr-2"
            >
                {{ member.user.username }} ({{ member.role.name }})
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
    </v-data-table>
    <PopupComponent title="Добавить команду" v-if="showAdd" :showButtonClose="true" @close="showAdd = false">
        <template #content>
            <v-text-field v-model="name" label="Название"></v-text-field>
            <v-text-field v-model="description" label="Описание"></v-text-field>
            <v-select
                :items="roleNames"
                v-model="selectedRole"
                label="Кем Вы являетесь" />
            <hr />
            <div class="header">
                <v-btn @click="clearAll()" >Отменить</v-btn>
                <v-btn @click="createTeam" v-if="showButtonSave">Добавить</v-btn>
            </div>
        </template>
    </PopupComponent>

</template>

<style scoped>

</style>

<script>

export default defineComponent({
    name: "MyTeams",
    components: {
        PopupComponent
    },
    data() {
        return {
            showAdd: false,
            items: [],
            uTeam: useTeam(),
            uUser: userUser(),
            myRoles: [],
            selectedRole: "",
            name: "",
            description: "",
            headers: [
                {
                    title: 'ID',
                    value: 'id',
                },
                {
                    title: "name",
                    value: "name"
                },
                {
                    title: "description",
                    value: "description"
                },
                {
                    title: "Участники",
                    value: "members"
                },
                {
                    title: "Требуется",
                    value: "needRoles"
                },                 
                {
                    title: "Завершен набор",
                    value: "completed"
                },
                {
                    title: "Дата создания",
                    value: "createdAt"
                },
                {
                    title: "Действия",
                    value: "actions"
                },                                
            ]
        }
    },
    methods: {
        fromResponseToitem(item) {
            return {
                id: item.id,
                name: item.name,
                description: item.description,
                completed: item.completed,
                createdAt: DateTime.fromISO(item.createdAt).toLocaleString(DateTime.DATETIME_MED),
                members: item.members,
                needRoles: item.needRoles,
            }
        },
        clearAll() {
            this.name = ""
            this.description = ""
            this.selectedRole = ""
            this.showAdd = false
        },
        async createTeam() {
            const res = await this.uTeam.createTeam(this.name, this.description)
            if (res) {
                this.items.push(this.fromResponseToitem(res))
                await this.uTeam.enter(res.id, localStorage.getItem("userId"), this.selectedRoleToId)
                this.clearAll()
            }
        },
        async deleteTeam(teamId) {
            const res = await this.uTeam.deleteTeam(teamId)
            if (res) {
                this.items = this.items.filter(item => item.id !== teamId)
            }
        }
    },
    computed: {
        roleNames() {
            return this.myRoles.map(item => item.name)
        },
        showButtonSave() {
            return this.name.length > 0 && this.description.length > 0 && this.selectedRole.length > 0
        },
        selectedRoleToId() {
            return this.myRoles.find(item => item.name === this.selectedRole).id
        },
    },
    async mounted() {
        this.items = (await this.uTeam.getAllMyTeams()).map(item => this.fromResponseToitem(item))
        this.myRoles = await this.uUser.myRoles()
    }
})
</script>