import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import React, {Component} from 'react';
import Home from './pages/Home';
import Employees from "./pages/Employees";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/" exact={true} component={Home}/>
                    <Route path="/empls" exact={true} component={Employees}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
