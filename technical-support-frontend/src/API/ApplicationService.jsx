import axios from "axios";
import {authHeader} from "../utils/HeaderUtils";
import {AuthService} from "./AuthService";

const BASE_URL = "/api/application";

const user = AuthService.isAuthenticated().user;

export default class ApplicationService {
    static async getNewApplications() {
        const response = await axios.get(`${BASE_URL}/new`, authHeader())
        return getApplicationsByOwner(response.data)
    }

    static async getActiveApplications() {
        const response = await axios.get(`${BASE_URL}/active`, authHeader())
        return getApplicationsByOwner(response.data)
    }

    static async getArchiveApplications() {
        const response = await axios.get(`${BASE_URL}/archive`, authHeader())
        return getApplicationsByOwner(response.data)
    }

    static async getApplication(pathname) {
        const response = await axios.get(`${BASE_URL}/${pathname}`, authHeader())
        return response.data
    }

    static async changeStatus(application, newStatus) {
        console.log(application)
        application.basedApplicationDto.status = newStatus
        return await axios.post(`${BASE_URL}/change-status`, application, authHeader())
    }

}

function getApplicationsByOwner(applications) {
    console.log(user, "\n", applications)
    if (user.role === "Работник")
        return applications.filter((application) => application.basedApplicationDto.initiator.staffNumber === user.staffNumber)
    if (user.role === "Специалист технической поддержки")
        return applications.filter((application) => application.basedApplicationDto.executor.staffNumber === user.staffNumber)
}