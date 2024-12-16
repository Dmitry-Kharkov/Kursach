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
                :items="roleCategoriesNames"
                v-model="selectedRoleCategory"
                label="Какая сфера?" />
            <hr />
            <v-select
                :items="teamRoleNames"
                v-model="selectedTeamRoles"
                multiple
                label="Кто требуется?" />
            <hr />            
            <v-select
                clearable
                :items="roleNames"
                v-if="roleNames.length > 0"
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
import PopupComponent from '@/components/PopupComponent.vue'
import { defineComponent } from 'vue';
import {useApplication} from '@/store/applications';
import { useTeam } from '@/store/team';
import { DateTime } from 'luxon';
import { userUser } from '@/store/user';
import { useRole } from '@/store/roles';

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
            uRole: useRole(),
            roleCategories: [],
            teamRoles: [],
            selectedTeamRoles: [],
            myRoles: [],
            selectedRole: "",
            selectedRoleCategory: "",
            name: "",
            description: "",
            a: ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'],
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
            const res = await this.uTeam.createTeam(this.name, this.description, this.selectedRoleCategoryId, this.selectedRolesToIds)
            if (res) {
                this.items.push(this.fromResponseToitem(res))
                if (this.selectedRole != '') {
                    await this.uTeam.enter(res.id, localStorage.getItem("userId"), this.selectedRoleToId)
                    this.items = (await this.uTeam.getAllMyTeams()).map(item => this.fromResponseToitem(item))
                }
                this.clearAll()
            }
        },
        async deleteTeam(teamId) {
            const res = await this.uTeam.deleteTeam(teamId)
            if (res) {
                this.items = this.items.filter(item => item.id !== teamId)
            }
        },
        getRoleCategoryIdByName(name) {
            return this.roleCategories.find(item => item.name === name).id
        }
    },
    watch: {
        async selectedRoleCategory(newVal) {
            this.teamRoles = await this.uRole.getAllRoles(this.getRoleCategoryIdByName(newVal))
        }
    },
    computed: {
        roleNames() {
            return this.myRoles.filter(item => this.selectedRolesToIds.includes(item.id)).map(item => item.name)
        },
        showButtonSave() {
            return this.name.length > 0 && this.description.length > 0 && this.selectedRoleCategory != "" && this.selectedTeamRoles.length > 0
        },
        selectedRoleToId() {
            return this.myRoles.find(item => item.name === this.selectedRole).id
        },
        roleCategoriesNames() {
            return this.roleCategories.map(item => item.name)
        },
        teamRoleNames() {
            return this.teamRoles.map(item => item.name)
        },
        selectedRoleCategoryId() {
            return this.roleCategories.find(item => item.name === this.selectedRoleCategory).id
        },
        selectedRolesToIds() {
            return this.selectedTeamRoles.map(item => this.teamRoles.find(role => role.name === item).id)
        }
    },
    async mounted() {
        this.items = (await this.uTeam.getAllMyTeams()).map(item => this.fromResponseToitem(item))
        this.myRoles = await this.uUser.myRoles()
        this.roleCategories = await this.uRole.getRoleCategories()
    }
})
</script>