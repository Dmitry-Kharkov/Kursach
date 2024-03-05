import axios from "axios";

let HOST = 'http://localhost:8070'
let API = '/api/v1/user/'

export default {

    getAll() {

        return axios.post(HOST + API + 'get-all',
            {},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    },


    editPassword(password) {
        return axios.post('http://localhost:8070/api/v1/user/edit-pwd',
            {"password":password},
             {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    },

    addUser(user) {

        return axios.post('http://localhost:8070/api/v1/user/add',
            {"name":user.name,
            "login":user.login,
            "password":user.password},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
        })

    },


    userLogin(login,password) {

        return axios.post('http://localhost:8070/api/v1/user/login',
            {"login":login,"password":password},
                          
            "from":searchUsers.from,
            "count":searchUsers.count},

            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )               
       },
                          
    searchUsers(searchUsers) {

        return axios.post(HOST + API + 'search',
            {"searchValue":searchUsers.searchValue,
            "from":searchUsers.from,
            "count":searchUsers.count},

            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )


    },

    }



}