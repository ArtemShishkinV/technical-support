import React, {useContext} from 'react';
import {AppContext} from "../AppContext";
import {UserInfo} from "../components/UserInfo";

export const Profile = () => {
    const context = useContext(AppContext)

    return (
        <div>
            <UserInfo user={context.user}/>
        </div>
    );
};