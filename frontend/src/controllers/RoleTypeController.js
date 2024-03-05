import axios from "axios";


export default {

    getAll() {

        return axios.post('http://localhost:8070/api/v1/role-type/get-all',
            {},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    },

    editRoleType(roleType) {

        return axios.post('http://localhost:8070/api/v1/role-type/edit',
            {
                "id": roleType.id,
                "name": roleType.name,
                "description": roleType.description
            },
            {
                headers: {
                    'Content-Type': "application/json;charset=utf-8"
                }
            }
        )
    }



}