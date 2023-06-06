import React from 'react';
import {BrowserRouter as Router, Redirect, Route, Switch} from "react-router-dom";
import Home from "../pages/Home";
import Employees from "../pages/Employees";
import {Profile} from "../pages/Profile";
import Devices from "../pages/Devices";
import Applications from "../pages/Applications";
import {Application} from "../pages/Application";
import {CreateApplication} from "../pages/CreateApplication";
import Login from "../pages/Login";
import {CreateApplicationSuccess} from "../pages/CreateApplicationSuccess";

export const AppRouter = () => {
        const isAuth = localStorage.getItem("auth");
        console.log(isAuth)


        return (
            isAuth
                ?
                <Router>
                    <Switch>
                        <Route path="/" exact={true} component={Home}/>
                        <Route path="/employees" exact={true} component={Employees}/>
                        <Route path="/profile" exact={true} component={Profile}></Route>
                        <Route path="/devices" exact={true} component={Devices}></Route>
                        <Route path="/applications" exact={true} component={Applications}/>
                        <Route path="/applications/:category/:id" exact={true} component={Application}/>
                        <Route path="/create-application" exact={true} component={CreateApplication}/>
                        <Route path="/create-application/success/:category/:id" exact={true} component={CreateApplicationSuccess}/>
                        <Redirect to="/"/>
                    </Switch>
                </Router>
                :
                <Router>
                    <Switch>
                        <Route path="/login" exact={true} component={Login}/>
                        <Redirect to="/login"/>
                    </Switch>
                </Router>
        )
    }
;