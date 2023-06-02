import React from 'react';
import "../css/Applications.css";
import {ApplicationPriorityIcon} from "./UI/ApplicationPriorityIcon";
import {getDatetimeToString} from "../utils/DatetimeUtils";
import {DefaultButton} from "./UI/DefaultButton";
import {useHistory} from "react-router-dom";
import {getApplicationCategoryUrl, getApplicationColorByPriority} from "../utils/ApplicationUtils";

export const ApplicationListItem = ({application}) => {
    const navigate = useHistory();

    const getApplicationUrl = () => {
        return `/applications/${getApplicationCategoryUrl(application.category).url}/${application.basedApplicationDto.id}`
    }

    const applicationColor = getApplicationColorByPriority(application)
    const applicationBorder = {
        border: `3px double ${applicationColor}`
    }
    console.log(applicationColor)

    return (
        <div className="application" style={applicationBorder}>
            <div className="application-title application-div">
                <div className="application-id">
                    <ApplicationPriorityIcon color={applicationColor}/>
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
                    className="application-date">{getDatetimeToString(application.basedApplicationDto.createdAt)}</div>
            </div>
            <div className="application-go">
                <DefaultButton style={applicationBorder} onClick={() => navigate.push(getApplicationUrl())}>
                    Перейти
                </DefaultButton>
            </div>
        </div>
    );
};
