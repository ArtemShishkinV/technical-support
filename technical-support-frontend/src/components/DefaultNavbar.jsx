import React from 'react';
import "../css/Navbar.css";
import {DefaultButton} from "./UI/DefaultButton";
import {AuthService} from "../API/AuthService";
import {useHistory} from "react-router-dom";


export const DefaultNavbar = () => {
    const history = useHistory();

    const logout = () => {
        localStorage.removeItem('user')
        localStorage.removeItem("auth")
        console.log("Выход...")
        history.push('/login');
        window.location.reload();
    }


    const auth = AuthService.isAuthenticated()

    const getEmployeesLink = () => {
        if (auth.user.role === "Специалист технической поддержки")
            return <li><a href="/employees">Пользователи</a></li>
    }

    console.log(auth)

    return (
        auth ?
            <div className="navbar__wrapper">
                <div className="container">
                    <div className="navbar">
                        <ul className="navbar__list">
                            <li><a href="/profile">Профиль</a></li>
                            <li><a href="/applications">Заявки</a></li>
                            <li><a href="/devices">Устройства</a></li>
                            {getEmployeesLink()}
                        </ul>
                        <div className="logout__wrapper">
                            <DefaultButton children="Выйти" onClick={logout}/>
                        </div>
                    </div>
                </div>
            </div>
            :
            <div className="navbar__wrapper">
                <div className="container">
                    <div className="navbar__title">
                        {/*Добро пожаловать!*/}
                    </div>
                </div>
            </div>
    );
};