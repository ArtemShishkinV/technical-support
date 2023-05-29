import axios from "axios";

const BASE_URL = "/api/application/create";

export default class CreateApplicationService {

    // static async getAllModels() {
    //     const prioritiesRes = await axios.get("/api/priority")
    //     const applicationDeviceTypesRes = await axios.get("/api/application-type/device")
    //     const applicationSoftwareTypesRes = await axios.get("/api/application-type/software")
    //     const devices = await axios.get("/api/device/types")
    //     const software = await axios.get("/api/software/types")
    //     return {
    //         priorities: prioritiesRes.data,
    //         applicationDeviceTypes: applicationDeviceTypesRes.data,
    //         applicationSoftwareTypes: applicationSoftwareTypesRes.data,
    //         deviceTypes: devices.data,
    //         softwareTypes: software.data
    //     }
    // }
    static async getAllModels(user) {
        console.log(user)
        return axios.get(`${BASE_URL}/models/${user.staffNumber}`)
    }

    static async create(application) {
        console.log(application)
        const response = await axios.post(BASE_URL, application)
        return response.data;
    }
}