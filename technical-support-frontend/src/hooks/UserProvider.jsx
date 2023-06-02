import {createContext, useEffect, useState} from "react";
import Login from "../pages/Login";
import {AuthService} from "../API/AuthService";

const UserContext = createContext(null);

export const UserProvider = ({ children }) => {
    const [ currentUser, setCurrentUser ] = useState(undefined);

    useEffect(() => {
        const checkLoggedIn = async () => {
            let cuser = AuthService.isAuthenticated();
            if (cuser === null) {
                localStorage.setItem('user', '');
                cuser = '';
            }

            setCurrentUser(cuser);
        };

        checkLoggedIn();
    }, []);

    console.log('usercontext', currentUser);

    return (
        <UserContext.Provider value={[currentUser, setCurrentUser]}>
            { currentUser?.token ? children : <Login />}
        </UserContext.Provider>
    );
};


export default UserContext;