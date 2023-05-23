import {BrowserRouter, BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import React, {Component} from 'react';
import Home from './pages/Home';
import Employees from "./pages/Employees";
import Applications from "./pages/Applications";
import {DefaultNavbar} from "./components/DefaultNavbar";

class App extends Component {
    render() {
        return (
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
    )
    }
    }

    export default App;
