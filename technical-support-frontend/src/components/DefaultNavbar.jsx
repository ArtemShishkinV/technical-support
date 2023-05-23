import React from 'react';

export const DefaultNavbar = () => {
    const logout = () => {
        // setIsAuth(false);
        // localStorage.removeItem('auth')
        console.log("Выход...")
    }

    return (
        <div>
            <div>
                <ul>
                    <li><a href="/">Профиль</a></li>
                    <li><a href="/applications">Заявки</a></li>
                    <li><a href="/devices">Устройства</a></li>
                </ul>
            </div>
            <div>
                <button onClick={logout}>Выйти</button>
            </div>
        </div>
    );
};