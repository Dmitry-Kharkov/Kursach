import axios from "axios";


export default {

    getAll() {

        return axios.post('http://localhost:8070/api/v1/user/get-all',
            {},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    },

    userLogin(login,password) {

        return axios.post('http://localhost:8070/api/v1/user/login',
            {"login":login,"password":password},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    },



}