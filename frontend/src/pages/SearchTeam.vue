<script setup>
import PopupComponent from '@/components/PopupComponent.vue'
import { defineComponent } from 'vue';
import { useTeam } from '@/store/team';
import { DateTime } from 'luxon';
import { userUser } from '@/store/user';
import { useRole } from '@/store/roles';
</script>

<template>
    <div class="header">
        <h1>Поиск команды</h1>
    </div>
    <div class="panel-inline">
        <v-select
            :items="rolesNames"
            v-model="selectedUserRole"
            label="Выберите роль участника"
        />
        <v-select
            :items="rolesNames"
            v-model="selectedNeedRole"
            label="Выберите роль нужного участника"
        />
        <v-select
            :items="['Не выбрано', 'Завершен', 'Не завершен']"
            v-model="selectedDone"
            label="Выберите роль нужного участника"
        />     
        <label>
            Дата от:
            <input type="date" v-model="selectedDateFrom" />   
        </label>
        <label>
            Дата до:
            <input type="date" v-model="selectedDateTo" />
        </label>
    </div>
    <v-data-table-server
        :headers="headers"
        :items="filtered"
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
        <template #item.createdAt="{ item }">
            {{ DateTime.fromISO(item.createdAt).toLocaleString(DateTime.DATETIME_MED) }}
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
    </v-data-table-server>
</template>

<style scoped>

</style>

<script>

export default defineComponent({
    name: "SearchTeam",
    components: {
        PopupComponent
    },
    data() {
        return {
            items: [],
            uTeam: useTeam(),
            uUser: userUser(),
            uRoles: useRole(),
            selectedUserRole: "Не указано",
            selectedNeedRole: "Не указано",
            selectedDone: "Не указано",
            selectedDateFrom: "",
            selectedDateTo: "",
            myRoles: [],
            roles: [],
            headers: [
                {
                    title: 'ID',
                    value: 'id',
                },
                {
                    title: "Владелец",
                    value: "owner"
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
            ]
        }
    },
    methods: {
        fromResponseToitem(item) {
            return {
                id: item.id,
                owner: item.owner.username,
                name: item.name,
                description: item.description,
                completed: item.completed,
                createdAt: item.createdAt,
                members: item.members,
                needRoles: item.needRoles,
            }
        },
        needRoles(teamId) {
            return this.items?.find(item => item.id == teamId)?.needRoles || []
        },
        enterRoles(teamId) {
            const needRoles = this.needRoles(teamId)
            return this.myRoles.filter(role => !needRoles.includes(role))
        },
        enterRolesNames(teamId) {
            return this.enterRoles(teamId).map(role => role.name)
        },
        canEnter(teamId) {
            return this.needRoles(teamId).some(role => this.myRoles.includes(role.id))
        },
    },
    computed: {
        rolesNames() {
            return ["Не указано"].concat(this.roles.map(item => item.name))
        },
        filtered() {
            let filtered = this.items
            if (this.selectedUserRole != "Не указано") {
                filtered = filtered.filter(item => item.members.some(member => member.role.name == this.selectedUserRole))
            }
            if (this.selectedNeedRole != "Не указано") {
                filtered = filtered.filter(item => item.needRoles.some(role => role.name == this.selectedNeedRole))
            }
            if (this.selectedDone != "Не указано") {
                filtered = filtered.filter(item => item.completed == (this.selectedDone != "Завершен"))
            }
            if (this.selectedDateFrom != "") {
                filtered = filtered.filter(item => DateTime.fromISO(item.createdAt) >= DateTime.fromISO(this.selectedDateFrom))
            }
            if (this.selectedDateTo != "") {
                filtered = filtered.filter(item => DateTime.fromISO(item.createdAt) <= DateTime.fromISO(this.selectedDateTo))
            }
            return filtered
        }
    },
    async mounted() {
        this.items = (await this.uTeam.allTeams()).map(item => this.fromResponseToitem(item))
        this.myRoles = (await this.uUser.myRoles()).map(item => item.id)
        this.roles = (await this.uRoles.getAllRoles())
    }
})
</script>