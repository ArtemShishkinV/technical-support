import React, {useEffect, useState} from 'react';
import "../css/Employees.css";
import {DefaultInput} from "../components/UI/DefaultInput";
import {DefaultButton} from "../components/UI/DefaultButton";
import PhoneInput from 'react-phone-input-2';
import {useFetching} from "../hooks/UseFetching";
import EmployeeService from "../API/EmployeeService";
import {DefaultSelect} from "../components/UI/DefaultSelect";

export const CreateEmployee = () => {
    const [phoneNumber, setPhoneNumber] = useState()
    const [models, setModels] = useState()

    const [fetchModels, isLoading, _] = useFetching(async () => {
        const resp = await EmployeeService.getModels()
        setModels(resp.data)
    })

    const test = () => {

    }

    useEffect(() => {
        fetchModels()
    }, [])

    return (
        <div>
            <div className="container">
                <div className="create-employee__inner">
                    <h1>Создание пользователя</h1>
                    <div className="create-employee__main">
                        <DefaultInput
                            placeholder="Введите имя"
                        />
                        <DefaultInput
                            placeholder="Введите фамилию"
                        />
                        <DefaultInput
                            placeholder="Введите отчество"
                        />
                        <DefaultInput
                            type="email"
                            placeholder="Введите электронную почту"
                        />
                        <DefaultInput
                            type="password"
                            placeholder="Введите пароль"
                        />
                        <PhoneInput
                            className="phoneInput"
                            placeholder="Введите номер телефона"
                            specialLabel=""
                            country={'ru'}
                            value={phoneNumber}
                            onChange={setPhoneNumber}/>
                        <DefaultSelect
                            defaultValue={{id: 0, title: "Выберите роль"}}
                            options={[{id: 1, title: "Работник"}]}
                            onChange={test()}
                        />
                        <DefaultSelect
                            defaultValue={{id: 0, title: "Выберите отдел"}}
                            options={[{id: 1, title: "Отдел разработки"}]}
                            onChange={test()}
                        />
                        <DefaultSelect
                            defaultValue={{id: 0, title: "Выберите должность"}}
                            options={[{id: 1, title: "Инженер"}]}
                            onChange={test()}
                        />
                        <DefaultSelect
                            defaultValue={{id: 0, title: "Выберите офис"}}
                            options={[{id: 1, title: "Москва, Кутузова, 5"}]}
                            onChange={test()}
                        />
                        <DefaultSelect
                            defaultValue={{id: 0, title: "Выберите рабочее место"}}
                            options={[{id: 1, title: "4-К6-1"}]}
                            onChange={test()}
                        />
                        <DefaultButton>
                            Создать
                        </DefaultButton>
                    </div>
                </div>
            </div>
        </div>
    );
};