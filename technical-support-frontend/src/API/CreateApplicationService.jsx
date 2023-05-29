import axios from "axios";

export default class CreateApplicationService {
    static async getAllModels() {
        const prioritiesRes = await axios.get("/api/priority")
        const applicationDeviceTypesRes = await axios.get("/api/application-type/device")
        const applicationSoftwareTypesRes = await axios.get("/api/application-type/software")
        return {
            priorities: prioritiesRes.data,
            applicationDeviceTypes: applicationDeviceTypesRes.data,
            applicationSoftwareTypes: applicationSoftwareTypesRes.data,
        }
    }
}