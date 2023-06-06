import React, {useEffect, useState} from 'react';
import {DefaultInput} from "../components/UI/DefaultInput";
import {DefaultButton} from "../components/UI/DefaultButton";
import "../css/Login.css"
import {AuthService} from "../API/AuthService";
import {useHistory} from "react-router-dom";

const Login = () => {
        const history = useHistory()
        const [err, setErr] = useState("")

        async function handleLogin(event) {
            event.preventDefault();
            const {username, password} = document.forms[0]
            const resp = await AuthService.login(username.value, password.value)
            console.log(resp)
            resp === "Ошибка" ? setErr("Неверный логин или пароль!") : successLogin()
        }

        function successLogin() {
            localStorage.setItem("auth", "true")
            history.push("/")
            window.location.reload();
        }

        return (
            <div>
                <div className="container">
                    <div className="login__inner">
                        <h3>Тут нужно ввести данные для входа</h3>
                        <form onSubmit={handleLogin}>
                            {/*/!*{err*!/*/}
                            {/*    ? */}
                            {/*    // : <div></div>*/}
                            {/*}*/}
                            <div className="login__error-message">{err}</div>
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