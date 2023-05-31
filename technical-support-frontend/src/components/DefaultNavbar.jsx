import React, {useContext} from 'react';
import "../css/Navbar.css";
import {DefaultButton} from "./UI/DefaultButton";
import {AppContext} from "../AppContext";


export const DefaultNavbar = () => {
    const logout = () => {
        // setIsAuth(false);
        // localStorage.removeItem('auth')
        console.log("Выход...")
    }

    const user = useContext(AppContext)

    const getEmployeesLink = () => {
        if (user.role === "Специалист технической поддержки")
            return <li><a href="/employees">Пользователи</a></li>
    }

    return (
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
                        <DefaultButton children="Выйти"/>
                    </div>
                </div>
            </div>
        </div>
    );
};