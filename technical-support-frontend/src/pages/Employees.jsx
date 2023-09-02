import React, {useEffect, useState} from 'react';
import {useFetching} from "../hooks/UseFetching";
import EmployeeService from "../API/EmployeeService";
import {DefaultLoader} from "../components/UI/DefaultLoader";
import {DefaultButton} from "../components/UI/DefaultButton";
import {useHistory} from "react-router-dom";
import "../css/Employees.css";
import {EmployeeListItem} from "../components/EmployeeListItem";

const Employees = () => {
    const [employees, setEmployees] = useState([]);
    const navigate = useHistory();

    const [fetchEmployees, isLoading, _] = useFetching(async () => {
        const resp = await EmployeeService.getAll();
        setEmployees(resp.data)
    })

    const redirectCreateEmployee = () => {
        navigate.push("/create-employee")
    }

    useEffect(() => {
        fetchEmployees()
    }, []);

    return (
        <div>
            <div className="container">
                <div className="employees__inner">
                    <DefaultButton
                        onClick={redirectCreateEmployee}
                    >Создать пользователя</DefaultButton>
                </div>
                <h1>Список пользователей</h1>
                {isLoading
                    ? <DefaultLoader/>
                    :
                    <div className="employees-list">
                        {employees.map(employee => <EmployeeListItem employee={employee}/>)}
                    </div>

                }
            </div>

        </div>
    );
};

export default Employees;
