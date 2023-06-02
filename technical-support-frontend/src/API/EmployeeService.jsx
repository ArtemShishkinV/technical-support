import axios from "axios";

const BASE_URL = "/api/employees";

export default class EmployeeService {
    static async getById(id, token) {
        return axios.get(`${BASE_URL}/${id}`, {
            headers: {
                'Authorization': token
            }
        })
    }
}