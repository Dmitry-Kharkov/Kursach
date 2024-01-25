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

    searchUsers() {

        return axios.post(HOST + API + 'search',
            {"login":"admin"},
            {
                headers : {
                    'Content-Type' : "application/json;charset=utf-8"
                }
            }
        )

    }



}