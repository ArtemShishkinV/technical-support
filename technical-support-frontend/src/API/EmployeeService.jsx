import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";

const BASE_URL = "/api/employees";

export default class EmployeeService {
    static async getById(id, token) {
        return axios.get(`${BASE_URL}/${id}`, {
            headers: {
                'Authorization': token
            }
        })
    }

    static async getAll() {
        return axios.get(`${BASE_URL}`, authHeader());
    }

    static async getModels() {
        return axios.get(`${BASE_URL}/models`, authHeader())
    }
}