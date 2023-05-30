import axios from "axios";

const BASE_URL = "/api/application";

export default class ApplicationService {
    static async getNewApplications() {
        const response = await axios.get(`${BASE_URL}/new`)
        return response.data
    }

    static async getActiveApplications() {
        const response = await axios.get(`${BASE_URL}/active`)
        return response.data
    }

    static async getArchiveApplications() {
        const response = await axios.get(`${BASE_URL}/archive`)
        return response.data
    }
    static async getApplication(pathname) {
        const response = await axios.get(`${BASE_URL}/${pathname}`)
        return response
    }
}