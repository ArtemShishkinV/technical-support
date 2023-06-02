import React from 'react';
import {DefaultInput} from "../components/UI/DefaultInput";
import {DefaultButton} from "../components/UI/DefaultButton";
import "../css/Login.css"
import {AuthService} from "../API/AuthService";
import {useHistory} from "react-router-dom";

const Login = () => {
        const history = useHistory()

        async function handleLogin(event) {
            event.preventDefault();
            const {username, password} = document.forms[0]
            const resp = await AuthService.login(username.value, password.value)
            console.log(resp)
            localStorage.setItem("auth", "true")
            history.push("/")
            window.location.reload();
        }

        return (
            <div>
                <div className="container">
                    <form className="login__inner" onSubmit={handleLogin}>
                        <DefaultInput name="username" type="text" placeholder="Введите логин"/>
                        <DefaultInput name="password" type="password" placeholder="Введите пароль"/>
                        <DefaultButton type="submit">Войти</DefaultButton>
                    </form>
                </div>
            </div>
        );
    }
;

export default Login;