import React, {useMemo, useState} from 'react';
import {DefaultButton} from "./UI/DefaultButton";
import "../css/Application.css";
import {getButtonByApplicationStatus, getPropsByUserRole} from "../utils/ApplicationUtils";
import {AuthService} from "../API/AuthService";
import {DefaultCollapse} from "./UI/DefaultCollapse";
import UserInfoMain from "./UserInfoMain";
import DeviceListItem from "./DeviceListItem";
import {useFetching} from "../hooks/UseFetching";
import DeviceService from "../API/DeviceService";
import {SoftwareListItem} from "./SoftwareListItem";
import DefaultComment from "./DefaultComment";
import {DefaultLoader} from "./UI/DefaultLoader";

const ApplicationIdPage = ({application}) => {
    const context = AuthService.isAuthenticated();

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
                <h2>
                    {application.category}
                </h2>
                <div className="application__info">
                    {application.category === "Заявка на технику"
                        ? <DeviceListItem
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
                        : <SoftwareListItem
                            software={applicationObject}
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
                    }
                </div>
                <DefaultCollapse title={targetUser.collapseTitle}>
                    <UserInfoMain user={targetUser.applicationUser}/>
                </DefaultCollapse>
                <DefaultCollapse title="Комментарии">
                    <DefaultComment/>
                </DefaultCollapse>

            </div>
        );
    } else {
        return
        <div>
            <div className="conatainer">
                <DefaultLoader/>
            </div>
        </div>
    }
};

export default ApplicationIdPage;