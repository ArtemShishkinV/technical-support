import axios from "axios";

const BASE_URL = "/api/application/create";

export default class CreateApplicationService {
    static async getAllModels(user) {
        return axios.get(`${BASE_URL}/models/${user.staffNumber}`)
    }

    static async create(application) {
        console.log(application)
        return await axios.post(BASE_URL, application);
    }
}