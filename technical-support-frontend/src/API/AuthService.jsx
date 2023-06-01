import axios from "axios";

const API_URL = "/api/auth/";

export class AuthService {
    static (email, password) {
        return axios
            .post(API_URL + "login", {
                username: email,
                password
            })
            .then(response => {
                if (response.data.accessToken) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }

                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem('user'));
    }
}
