import React, {useEffect, useState} from 'react';
import axios from 'axios';

const Employees = () => {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        axios.get("/employees").then((data) => {
            console.log(data.data[0]);
            setEmployees(data?.data);
        });
    }, []);

    return (
        <div>
            <h1>Список работников</h1>
            {employees.map((employee, index) => (
                <p>{index + 1} - {employee.login}</p>
            ))}
        </div>
    );
};

export default Employees;
