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

    const getNavbarByRole = () => {
        if (auth.user.role === "Специалист технической поддержки")
            return (
                <ul className="navbar__list">
                    <li><a href="/profile">Профиль</a></li>
                    <li><a href="/applications">Заявки</a></li>
                    <li><a href="/employees">Пользователи</a></li>
                </ul>
            )
        return (
            <ul className="navbar__list">
                <li><a href="/profile">Профиль</a></li>
                <li><a href="/applications">Мои заявки</a></li>
                <li><a href="/devices">Мои устройства</a></li>
                {getEmployeesLink()}
            </ul>
        )

    }

    console.log(auth)

    return (
        <div className="navbar__wrapper">
            <div className="container">
                <div className="navbar">
                    {getNavbarByRole()}
                    <div className="logout__wrapper">
                        <DefaultButton children="Выйти" onClick={logout}/>
                    </div>
                </div>
            </div>
        </div>
    );
};