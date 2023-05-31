import React, {useContext} from 'react';
import {AppContext} from "../AppContext";
import {UserInfo} from "../components/UserInfo";

export const Profile = () => {
    const user = useContext(AppContext)

    return (
        <UserInfo user={user}/>
    );
};