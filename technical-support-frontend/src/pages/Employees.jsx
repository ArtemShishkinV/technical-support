import React, {useEffect, useState} from 'react';
import axios from 'axios';

const Employees = () => {
    const [employees, setEmployees] = useState([]);

    useEffect(() => {
        axios.get("/api/employees").then((data) => {
            console.log(data.data[0]);
            setEmployees(data?.data);
        });
    }, []);

    return (
        <div>
            <h1>Список работников</h1>
            <table>
                <thead>
                <tr>
                    <th>Табельный номер</th>
                    <th>ФИО</th>
                    <th>Электронная почта</th>
                    <th>Номер телефона</th>
                    <th>Роль</th>
                </tr>
                </thead>
                <tbody>
                {employees.map(employee => (
                    <tr>
                        <td>{employee.staffNumber}</td>
                        <td>{employee.lastName} {employee.firstName} {employee.middleName}</td>
                        <td>{employee.email}</td>
                        <td>{employee.phoneNumber}</td>
                        <td>{employee.role}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Employees;
