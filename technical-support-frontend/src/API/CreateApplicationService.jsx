import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";

const BASE_URL = "/api/application/create";

export default class CreateApplicationService {
    static async getAllModels(user) {
        return axios.get(`${BASE_URL}/models/${user.staffNumber}`, authHeader())
    }

    static async create(application) {
        console.log(application)
        return await axios.post(BASE_URL, application, authHeader());
    }
}