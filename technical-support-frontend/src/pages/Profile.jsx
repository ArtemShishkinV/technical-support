import React, {useContext} from 'react';
import {AppContext} from "../AppContext";
import {UserInfo} from "../components/UserInfo";
import {AuthService} from "../API/AuthService";

export const Profile = () => {
    const context = AuthService.isAuthenticated()
    console.log(context.user)
    return (
        <div>
            <UserInfo user={context.user}/>
        </div>
    );
};