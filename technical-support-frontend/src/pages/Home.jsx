import React from 'react';
import "../css/style.css";
import {AuthService} from "../API/AuthService";

const Home = () => {
    const context = AuthService.isAuthenticated();
    console.log(context.user)
    return (
        <div>
            <div className="container">
                <div className="info">
                    <h1>Добро
                        пожаловать, {context.user.lastName} {context.user.firstName} {context.user.middleName}!</h1>
                </div>
            </div>
        </div>
    );
};

export default Home;

