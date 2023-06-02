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
                    <div className="login__inner">
                        <h3>Вбей данные, чтобы понять кто ты по масти</h3>
                        <form onSubmit={handleLogin}>
                            <DefaultInput name="username" type="text" placeholder="Введите логин"/>
                            <DefaultInput name="password" type="password" placeholder="Введите пароль"/>
                            <DefaultButton type="submit">Войти</DefaultButton>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
;

export default Login;