import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";

const BASE_URL = "/api/application/device";

export default class ApplicationDeviceService {
    static async getById(id) {
        const response = await axios.get(`${BASE_URL}/${id}`, authHeader())
        return response.data
    }

    static async getAll() {
        const response = await axios.get(`${BASE_URL}`, authHeader())
        return response.data
    }

}