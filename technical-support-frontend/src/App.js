import {BrowserRouter} from 'react-router-dom';
import React from 'react';
import {DefaultNavbar} from "./components/DefaultNavbar";
import {AppRouter} from "./components/AppRouter";
import {UserProvider} from "./hooks/UserProvider";

function App() {

    return (
        <BrowserRouter>
            <UserProvider>
                <DefaultNavbar/>
                <AppRouter/>
            </UserProvider>
        </BrowserRouter>
    )
}

export default App;
