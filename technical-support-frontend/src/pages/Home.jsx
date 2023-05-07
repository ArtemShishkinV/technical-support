import React from 'react';
import {Link} from "react-router-dom";
import {Button} from "reactstrap";

const Home = () => {
    return (
        <div>
            <h1>Домашняя страница</h1>
            <Button><Link to="/employees">Clients</Link></Button>
        </div>
    );
};

export default Home;

