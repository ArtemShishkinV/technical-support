import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";

const BASE_URL = "/api/priority";

export default class PriorityService {
    static async getAll() {
        return axios.get(`${BASE_URL}`, authHeader())
    }
}