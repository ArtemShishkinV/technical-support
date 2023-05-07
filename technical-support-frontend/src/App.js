import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import React, {Component} from 'react';
import Home from './pages/Home';
import Home from './pages/Home';

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/" exact={true} component={Home}/>
                    <Route path="/employees" exact={true} component={Employees}/>
                    <Route path="/devices" exact={true} component={Devices}/>
                </Switch>
            </Router>
        )
    }
}

export default App;
