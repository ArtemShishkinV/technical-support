import {BrowserRouter} from 'react-router-dom';
import React, {useEffect, useState} from 'react';
import {DefaultNavbar} from "./components/DefaultNavbar";
import {AppContext} from "./AppContext";
import {user} from "./pages/data";
import {AppRouter} from "./components/AppRouter";

function App() {
    const [isAuth, setIsAuth] = useState(false);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        if (localStorage.getItem('auth')) {
            setIsAuth(true)
        }
        setLoading(false)
    }, [])

    return (
        //TODO: Сделать получение пользователя по запросу /api/profile
        <AppContext.Provider value={{
            user,
            isAuth,
            setIsAuth,
            isLoading
        }}>
            <BrowserRouter>
                <DefaultNavbar/>
                <AppRouter/>
            </BrowserRouter>
        </AppContext.Provider>
    )
}

export default App;
