import React from 'react';

export const EmployeeListItem = ({employee}) => {
    return (
        <div className={"employees-list__item"}>
            <div>Табельный номер: {employee.staffNumber}</div>
            <div>ФИО: {employee.lastName} {employee.firstName} {employee.middleName}</div>
            <div>Электронная почта: {employee.email}</div>
            <div>Номер телефона: {employee.phoneNumber}</div>
            <div>Отдел: {employee.department.title}</div>
            <div>Должность: {employee.post.title}</div>
            <div>Роль: {employee.role}</div>
            <div>
                Адрес офиса:
                г.{employee.officeDto.city},
                ул.{employee.officeDto.street},
                {employee.officeDto.buildNumber}
            </div>
            <div className="user-info__workplace">
                Рабочее место: {employee.workplace.floor}-{employee.workplace.roomNumber}-{employee.workplace.tableNumber}
            </div>
        </div>
    );
};