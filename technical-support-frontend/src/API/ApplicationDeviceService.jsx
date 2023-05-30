import axios from "axios";

const BASE_URL = "/api/application/device";

export default class ApplicationDeviceService {
    static async getById(id) {
        const response = await axios.get(`${BASE_URL}/${id}`)
        return response.data
    }

    static async getAll() {
        const response = await axios.get(`${BASE_URL}`)
        return response.data
    }

}