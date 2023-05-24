import React, {useContext} from 'react';
import "../css/style.css";
import {AppContext} from "../AppContext";

const Home = () => {
    const user = useContext(AppContext);

    return (
        <div>
            <div className="container">
                <div className="info">
                    <h1>Добро пожаловать, {user.lastName} {user.firstName} {user.middleName}!</h1>
                </div>
            </div>
        </div>
    );
};

export default Home;

