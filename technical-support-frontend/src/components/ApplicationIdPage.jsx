import React, {useEffect, useMemo, useState} from 'react';
import {DefaultButton} from "./UI/DefaultButton";
import "../css/Application.css";
import {getButtonByApplicationStatus, getPropsByUserRole} from "../utils/ApplicationUtils";
import {ApplicationPriorityIcon} from "./UI/ApplicationPriorityIcon";
import {AuthService} from "../API/AuthService";
import {DefaultCollapse} from "./UI/DefaultCollapse";
import UserInfoMain from "./UserInfoMain";
import DeviceListItem from "./DeviceListItem";
import {useFetching} from "../hooks/UseFetching";
import DeviceService from "../API/DeviceService";

const ApplicationIdPage = ({application}) => {
    const context = AuthService.isAuthenticated();
    console.log(application)

    const [applicationObject, setApplicationObject] = useState()

    const [fetchApplicationObject, isLoading, error] = useFetching(async () => {
        if (application.category === "Заявка на технику") {
            const response = await DeviceService.getBySerialNumber(application.applicationObjectDto.id);
            setApplicationObject(response.data)
        }
        if (application.category === "Заявка на ПО") {
            setApplicationObject(application.applicationObjectDto)
        }
    })

    useMemo(() => {
        fetchApplicationObject()
        console.log(applicationObject)
    }, [application])


    const getButtonByRole = () => {
        if (context.user.role === "Работник") {
            return (
                <div className="application__buttons application__buttons-solo">
                    <DefaultButton>
                        Отозвать заявку
                    </DefaultButton>
                </div>

            )
        }
        return getButtonByApplicationStatus(application.basedApplicationDto.status)
    }


    if (application && !isLoading) {
        const targetUser = getPropsByUserRole({user: context.user, application});
        return (
            <div className="application__inner">
                <ApplicationPriorityIcon priority={application.basedApplicationDto.priority}/>
                <h2>
                    {application.category}
                </h2>
                <div className="application__info">
                    <DeviceListItem
                        device={applicationObject}
                        children={
                        <div>
                            <div className="application__description">
                                <h4>Описание проблемы</h4>
                                <textarea disabled>{application.basedApplicationDto.description}</textarea>
                            </div>
                            {getButtonByRole()}
                        </div>
                        }
                    />
                </div>
                <DefaultCollapse title={targetUser.collapseTitle}>
                    <UserInfoMain user={targetUser.applicationUser}/>
                </DefaultCollapse>
                <DefaultCollapse title="Вложения">
                    <UserInfoMain user={targetUser.applicationUser}/>
                </DefaultCollapse>
                <DefaultCollapse title="Комментарии">
                    <UserInfoMain user={targetUser.applicationUser}/>
                </DefaultCollapse>

            </div>
        );
    }
};

export default ApplicationIdPage;