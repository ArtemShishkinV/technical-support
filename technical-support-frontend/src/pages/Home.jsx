import React from 'react';

const Home = () => {
    return (
        <div>
            <h1>Домашняя страница</h1>
            {/*<Link to="./Applications"> Список заявок </Link>*/}
            <ul>
                <li><a href="/employees">Список работников</a></li>
                <li><a href="/applications">Мои заявки</a></li>
            </ul>
        </div>
    );
};

export default Home;

