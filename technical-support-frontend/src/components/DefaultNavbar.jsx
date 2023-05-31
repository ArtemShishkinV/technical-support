import React, {useContext} from 'react';
import "../css/Navbar.css";
import {DefaultButton} from "./UI/DefaultButton";
import {AppContext} from "../AppContext";


export const DefaultNavbar = () => {
    const logout = () => {
        context.setIsAuth(false);
        localStorage.removeItem('auth')
        console.log("Выход...")
    }

    const context = useContext(AppContext)

    const getEmployeesLink = () => {
        if (context.user.role === "Специалист технической поддержки")
            return <li><a href="/employees">Пользователи</a></li>
    }

    console.log(context)

    return (
        context.isAuth
            ?
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