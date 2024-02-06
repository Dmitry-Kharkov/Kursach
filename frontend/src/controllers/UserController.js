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

    }



}