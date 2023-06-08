import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";

const BASE_URL = "/api/device";

export default class DeviceService {
    static async getByOwner(user) {
        return axios.get(`${BASE_URL}/owner/${user.staffNumber}`, authHeader())
    }

    static async getBySerialNumber(id) {
        return axios.get(`${BASE_URL}/${id}`, authHeader())
    }

    static async updateOwner(deviceDto) {
        return axios.post(`${BASE_URL}/update-owner`, deviceDto, authHeader())
    }

    static async updateCondition(deviceDto) {
        return axios.post(`${BASE_URL}/update-condition`, deviceDto, authHeader())
    }
}