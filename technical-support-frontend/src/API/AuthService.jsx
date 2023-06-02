import axios from "axios";

const API_URL = "/auth";

export class AuthService {
    static login(username, password) {
        return axios
            .post(API_URL, {
                username,
                password
            })
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    static logout() {
        localStorage.removeItem("user");
    }

    static isAuthenticated = () => {
        const user = localStorage.getItem('user');
        if (!user) {
            return {}
        }
        return JSON.parse(user);
    };
}