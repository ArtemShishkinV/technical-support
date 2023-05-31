import React, {useContext} from 'react';
import {AppContext} from "../AppContext";
import {DefaultLoader} from "./UI/DefaultLoader";
import {BrowserRouter as Router, Redirect, Route, Switch} from "react-router-dom";
import Home from "../pages/Home";
import Employees from "../pages/Employees";
import {Profile} from "../pages/Profile";
import Devices from "../pages/Devices";
import Applications from "../pages/Applications";
import {Application} from "../pages/Application";
import {CreateApplication} from "../pages/CreateApplication";
import Login from "../pages/Login";

export const AppRouter = () => {
    const context = useContext(AppContext);
    console.log(context)

    if (context.isLoading) {
        return <DefaultLoader/>
    }

    return (
        context.isAuth
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