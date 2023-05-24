import React from 'react';
import "../css/Navbar.css";
import {DefaultButton} from "./UI/DefaultButton";

export const DefaultNavbar = () => {
    const logout = () => {
        // setIsAuth(false);
        // localStorage.removeItem('auth')
        console.log("Выход...")
    }

    return (
        <div className="navbar__wrapper">
            <div className="container">
                <div className="navbar">
                    <ul className="navbar__list">
                        <li><a href="/">Профиль</a></li>
                        <li><a href="/applications">Заявки</a></li>
                        <li><a href="/devices">Устройства</a></li>
                    </ul>
                    <div className="logout__wrapper">
                        <DefaultButton children="Выйти"/>
                    </div>
                </div>
            </div>
        </div>
    );
};