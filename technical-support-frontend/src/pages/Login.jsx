import React, {useContext} from 'react';
import {DefaultInput} from "../components/UI/DefaultInput";
import {DefaultButton} from "../components/UI/DefaultButton";
import {AppContext} from "../AppContext";
import "../css/Login.css"

const Login = () => {
    const context = useContext(AppContext);

    const login = event => {
        event.preventDefault();
        context.setIsAuth(true);
        localStorage.setItem('auth', 'true')
    }

    return (
        <div>
            <div className="container">
                <form className="login__inner" onSubmit={login}>
                    <DefaultInput type="text" placeholder="Введите логин"/>
                    <DefaultInput type="password" placeholder="Введите пароль"/>
                    <DefaultButton>Войти</DefaultButton>
                </form>
            </div>
        </div>
    );
};

export default Login;