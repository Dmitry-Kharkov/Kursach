import axios from "axios";


export default {

    getAll() {

        return axios.post('http://localhost:8070/api/v1/role/get-all',
            {},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
            )

    },

    deleteRoleById(id) {

        return axios.post('http://localhost:8070/api/v1/role/delete',
            {"roleId" : id},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    },

    addRole(role){

        return axios.post('http://localhost:8070/api/v1/role/add',
            {
                "name" : role.name,
                "description" : role.description,
                "roleTypeId" : 0
            },
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    },

    editRole(role){

        return axios.post('http://localhost:8070/api/v1/role/edit',
            {
                "id" :role.id,
                "name" : role.name,
                "description" : role.description,
                "roleTypeId" : 0
            },
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    }


}