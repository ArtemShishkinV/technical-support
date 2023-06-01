import React, {useContext} from 'react';
import {DefaultInput} from "../components/UI/DefaultInput";
import {DefaultButton} from "../components/UI/DefaultButton";
import {AppContext} from "../AppContext";
import "../css/Login.css"
import axios from "axios";

const Login = () => {
        const context = useContext(AppContext);
        const formData = new FormData();

        async function login(event) {
            event.preventDefault();
            const {username, password} = document.forms[0]

            const resp = await axios.post("/api/auth", {
                username: username.value,
                password: password.value
            })
                .then((resp) => {
                    context.setIsAuth(true)
                    localStorage.setItem('auth', 'true')
                    console.log(resp.data)
                })
                .catch((reason) => console.log(reason))
        }

        return (
            <div>
                <div className="container">
                    <form className="login__inner" onSubmit={login}>
                        <DefaultInput name="username" type="text" placeholder="Введите логин"/>
                        <DefaultInput name="password" type="password" placeholder="Введите пароль"/>
                        <DefaultButton>Войти</DefaultButton>
                    </form>
                </div>
            </div>
        );
    }
;

export default Login;