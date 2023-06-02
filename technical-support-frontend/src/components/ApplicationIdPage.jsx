import React from 'react';
import {DefaultButton} from "./UI/DefaultButton";
import "../css/Application.css";
import {getButtonByApplicationStatus} from "../utils/ApplicationUtils";
import {ApplicationPriorityIcon} from "./UI/ApplicationPriorityIcon";
import {AuthService} from "../API/AuthService";

const ApplicationIdPage = ({application}) => {
    const context = AuthService.isAuthenticated();
    console.log(application)

    const getButtonByRole = () => {
        if (context.user.role === "Работник") {
            return (
                <DefaultButton>Отозвать заявку</DefaultButton>
            )
        }
        return getButtonByApplicationStatus(application.basedApplicationDto.status)
    }


    if (application) {
        return (
            <div className="application__inner">
                <ApplicationPriorityIcon priority={application.basedApplicationDto.priority}/>
                <h2>
                    {application.category}
                </h2>
                <div className="application__info">
                    <div>Тип обслуживания: {application.type}</div>
                    <div></div>
                    <div>
                        <textarea disabled>{application.basedApplicationDto.description}</textarea>
                    </div>
                    {getButtonByRole()}
                </div>
            </div>
        );
    }
};

export default ApplicationIdPage;