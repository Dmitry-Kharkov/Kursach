<script setup>
import { defineComponent } from 'vue';
import { userUser } from '@/store/user';
import { useRole } from '@/store/roles';
import { useTeam } from '@/store/team';
</script>

<template>
    <div class="header">
        <h1>Поиск участников команды</h1>
    </div>
    <div class="inline">
        <v-select
            :items="rolesNames"
            v-model="selectedRole"
            label="Выберите роль"
        />
        <v-select
            :items="teamNames"
            v-model="selectedTeam"
            label="Выберите команду"
        />        
    </div>
    <v-data-table
        :headers="headers"
        :items="filtered"
        :items-per-page="5"
        class="elevation-1"
    >
    <template #item.team="{ item }">
        <v-chip
            v-for="team in item.team"
            :key="team.id"
            :color="team.role === 'developer' ? 'primary' : 'secondary'"
            text-color="white"
            class="mr-2"
        >
            {{ team.team.name }} ({{ team.role.name }})
        </v-chip>
    </template>
    <template #item.role="{ item }">
        <v-chip
            v-for="role in item.role"
            :key="role.id"
            :color="role.role === 'developer' ? 'primary' : 'secondary'"
            text-color="white"
            class="mr-2"
        >
            {{ role.name }}
        </v-chip>
    </template>
    </v-data-table>

</template>

<style scoped>
.inline {
    display: flex;
    grid-gap: 10px;
}
</style>

<script>

export default defineComponent({
    name: "SearchTeamMember",
    data() {
        return {
            uUser: userUser(),
            uRoles: useRole(),
            uTeam: useTeam(),
            roles: [],
            tmpTeamNames: [],
            selectedRole: 'Не выбрано',
            selectedTeam: 'Не выбрано',
            items: [],
            headers: [
                {
                    title: 'ID',
                    value: 'id',
                },
                {
                    title: "Имя",
                    value: "username"
                },
                {
                    title: "Роль",
                    value: "role"
                },
                {
                    title: "В команде",
                    value: "team"
                },
            ]
        }
    },
    methods: {
        fromResponseToItems(response) {
            return response.map(item => {
                return {
                    id: item.id,
                    username: item.username,
                    role: item.roles,
                    team: item.members,
                }
            })
        },
    },
    computed: {
        filtered() {
            let result = this.items
            if (this.selectedRole != "Не выбрано") {
                result = result.filter(item => {
                    return item.role.some(role => role.name === this.selectedRole)
                })
            }
            if (this.selectedTeam != "Не выбрано") {
                result = result.filter(item => {
                    return item.team.some(team => team.team.name === this.selectedTeam)
                })
            }
            return result
        },
        rolesNames() {
            return ["Не выбрано"].concat( this.roles.map(role => role.name))
        },
        teamNames() {
            return ["Не выбрано"].concat( this.tmpTeamNames.map(team => team.name))
        }
    },
    async mounted() {
        this.roles = await this.uRoles.getAllRoles()
        this.tmpTeamNames = await this.uTeam.allTeamsName()
        this.items = this.fromResponseToItems(await this.uUser.getAllUsers())
    },
})
</script>