import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";

const BASE_URL = "/api/application";

export default class ApplicationService {
    static async getNewApplications() {
        const response = await axios.get(`${BASE_URL}/new`, authHeader())
        return response.data
    }

    static async getActiveApplications() {
        const response = await axios.get(`${BASE_URL}/active`, authHeader())
        return response.data
    }

    static async getArchiveApplications() {
        const response = await axios.get(`${BASE_URL}/archive`, authHeader())
        return response.data
    }
    static async getApplication(pathname) {
        const response = await axios.get(`${BASE_URL}/${pathname}`, authHeader())
        return response.data
    }
}