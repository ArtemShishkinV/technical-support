import React from 'react';
import "../css/Applications.css";
import {ApplicationPriorityIcon} from "./UI/ApplicationPriorityIcon";
import {getDatetimeToString} from "../utils/DatetimeUtils";
import {DefaultButton} from "./UI/DefaultButton";

export const ApplicationListItem = ({application}) => {
    return (
        <div className="application">
            <div className="application-title application-div">
                <div className="application-id">
                    <ApplicationPriorityIcon priority={application.basedApplicationDto.priority}/>
                    <div><b>#{application.basedApplicationDto.id}</b></div>
                    <div className="application-category">{application.category}</div>
                </div>
            </div>
            <div className="application-employee application-div">
                Клиент: {application.basedApplicationDto.executor.lastName} {application.basedApplicationDto.executor.firstName} {application.basedApplicationDto.executor.middleName}
            </div>
            <div className="application-type application-div">Тип услуги: {application.type}</div>
            <div className="application-object application-div">
                {application.applicationObjectDto.category}: {application.applicationObjectDto.title}
            </div>
            <div className="application-footer application-div">
                <div className="application-status">{application.basedApplicationDto.status}</div>
                <div
                    className="application-date">Создана: {getDatetimeToString(application.basedApplicationDto.createdAt)}</div>
            </div>
            <div className="application-go">
                <DefaultButton>Перейти</DefaultButton>
            </div>
        </div>
    );
};