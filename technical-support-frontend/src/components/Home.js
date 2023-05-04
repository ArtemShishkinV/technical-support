import {Component} from "react";
import { Button, Container } from 'reactstrap';
import AppNavbar from "./AppNavbar";
import {Link} from "react-router-dom";

export default class Home extends Component {
    render() {
        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <Button color="link"><Link to="/employees">Employees</Link></Button>
                </Container>
            </div>
        )
    }
}

