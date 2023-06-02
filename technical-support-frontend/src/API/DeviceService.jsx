import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";

const BASE_URL = "/api/device";

export default class DeviceService {
    static async getByOwner(user) {
        return axios.get(`${BASE_URL}/${user.staffNumber}`, authHeader())
    }
}