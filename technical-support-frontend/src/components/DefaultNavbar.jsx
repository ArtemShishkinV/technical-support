import React from 'react';

const DefaultNavbar = () => {
    return (
        <ul className="navbar">
            <li><a href="/">Профиль</a></li>
            <li><a href="/applications/{id}">Мои заявки</a></li>
            <li><a href="/devices/{id}">Мои устройства</a></li>
        </ul>
    );
};

export default DefaultNavbar;