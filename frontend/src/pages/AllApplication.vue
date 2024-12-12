<script setup>
import { defineComponent } from 'vue';
import {useApplication} from '@/store/applications';
import { DateTime } from 'luxon';
import { useRole } from '@/store/roles';
import PopupComponent from '@/components/PopupComponent.vue';
import { useTeam } from '@/store/team';
import { userUser } from '@/store/user';
</script>

<template>
    <div class="header">
        <h1>Все заявки</h1>
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
            <v-btn @click="selectedApplicationId = item.id" v-if="canEnter(item.id)">Присоединиться</v-btn>
            <p style="color: red" v-else>Нет подходящего роля</p>
        </template>
    </v-data-table>
    <PopupComponent title="Присоединение в команду" v-if="selectedApplicationId != ''" :showButtonClose="true" @close="selectedApplicationId = ''">
        <template #content>
            <v-select
                :items="enterRolesNames(selectedApplicationId)"
                v-model="selectedRole"
                label="Хочу вступить как" />
            <v-text-field v-model="description" label="Напишите коментарий"></v-text-field>
            <hr />
            <v-btn @click="enterToTeam()" v-if="selectedRole != ''">Присоединиться</v-btn>
        </template>
    </PopupComponent>
</template>

<style scoped>

</style>

<script>

export default defineComponent({
    name: "AllApplication",
    data() {
        return {
            showAdd: false,
            uApplication: useApplication(),
            uRoles: useRole(),
            items: [],

            description: "",
            uTeam: useTeam(),
            uUser: userUser(),
            myRoles: [],
            selectedApplicationId: "",
            selectedRole: "",            
            headers: [
                {
                    title: 'ID',
                    value: 'id',
                },
                {
                    title: "Заявитель",
                    value: "owner"
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

        fromResponseToItem(item) {
            return {
                id: item.id,
                name: item.name,
                owner: item.owner.username,
                description: item.description,
                createdAt: DateTime.fromISO(item.createdAt).toLocaleString(DateTime.DATETIME_MED),
                completed: item.completed,
                needRoles: item.needRoles
            }
        },
        
        needRoles(applicationId) {
            return this.items.find(item => item.id == applicationId)?.needRoles || []
        },
        needRolesIds(applicationId) {
            return this.needRoles(applicationId).map(role => role.id)
        },
        enterRoles(applicationId) {
            return this.myRoles.filter(role => this.needRolesIds(applicationId).includes(role.id))
        },
        enterRolesNames(applicationId) {
            return this.enterRoles(applicationId).map(role => role.name)
        },
        canEnter(applicationId) {
            return this.needRoles(applicationId).some(role => this.myRolesIds.includes(role.id))
        },
        async enterToTeam() {
            const res = await this.uTeam.applicationEnterToTeam(this.description, this.selectedApplicationId, this.roleIdBySelectedRoleName)
            if (res) {
                this.items = this.items.filter((item) => item.id !== this.selectedApplicationId)
            }
            this.selectedRole = ""
            this.description = ""
            this.selectedApplicationId = ""
        }        
    },
    computed: {
        myRolesIds() {
            return this.myRoles.map(role => role.id)
        },
        roleIdBySelectedRoleName() {
            return this.myRoles.find(role => role.name === this.selectedRole)?.id
        }
    },
   async mounted() {
        this.items = (await this.uApplication.allApplications()).map((item) => {
            return this.fromResponseToItem(item)
        })
        this.roles = await this.uRoles.getAllRoles()
        this.myRoles = (await this.uUser.myRoles())
    }
})
</script>