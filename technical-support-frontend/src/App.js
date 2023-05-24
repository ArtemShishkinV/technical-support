import {BrowserRouter, BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import React, {useState} from 'react';
import Home from './pages/Home';
import Employees from "./pages/Employees";
import Applications from "./pages/Applications";
import {DefaultNavbar} from "./components/DefaultNavbar";
import {AppContext} from "./AppContext";
import {user} from "./pages/data";

function App() {
    return (
        //TODO: Сделать получение пользователя по запросу /api/profile
        <AppContext.Provider value={
            user
        }>
            <BrowserRouter>
                <DefaultNavbar/>
                <Router>
                    <Switch>
                        <Route path="/" exact={true} component={Home}/>
                        <Route path="/employees" exact={true} component={Employees}/>
                        <Route path="/applications" exact={true} component={Applications}/>
                    </Switch>
                </Router>
            </BrowserRouter>
        </AppContext.Provider>
    )
}

export default App;
