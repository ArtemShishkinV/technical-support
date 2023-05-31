import React, {useContext} from 'react';
import "../css/style.css";
import {AppContext} from "../AppContext";

const Home = () => {
    const context = useContext(AppContext);

    return (
        <div>
            <div className="container">
                <div className="info">
                    <h1>Добро пожаловать, {context.user.lastName} {context.user.firstName} {context.user.middleName}!</h1>
                </div>
            </div>
        </div>
    );
};

export default Home;

