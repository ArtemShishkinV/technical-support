import React, {useMemo, useState} from 'react';
import "../css/Application.css";
import {
    getApplicationColorByPriority,
    getButtonByApplicationStatus,
    getPropsByUserRole
} from "../utils/ApplicationUtils";
import {AuthService} from "../API/AuthService";
import {DefaultCollapse} from "./UI/DefaultCollapse";
import UserInfoMain from "./UserInfoMain";
import DeviceListItem from "./DeviceListItem";
import {useFetching} from "../hooks/UseFetching";
import DeviceService from "../API/DeviceService";
import {SoftwareListItem} from "./SoftwareListItem";
import DefaultComment from "./DefaultComment";
import {DefaultModal} from "./DefaultModal";
import ApplicationService from "../API/ApplicationService";
import {ApplicationPriorityIcon} from "./UI/ApplicationPriorityIcon";
import {getDatetimeToString} from "../utils/DatetimeUtils";

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

    const getApplicationGeneralBlock = () => {
        return (
            <div className="application__info-general">
                <div>
                    Статус: {application.basedApplicationDto.status}
                </div>
                <div>
                    Дата создания: {getDatetimeToString(application.basedApplicationDto.createdAt)}
                </div>
                <div className="application__description">
                    <h4>Описание проблемы</h4>
                    <textarea disabled>{application.basedApplicationDto.description}</textarea>
                </div>
                {getButtonByRole()}
            </div>
        )
    }

    const getButtonByRole = () => {
        if (application.basedApplicationDto.status === "Отменена")
            return (
                <div>
                    <div className="application__buttons application__buttons-solo">
                        Заявка отменена!
                    </div>
                    <div className="application__description">
                        <h4>Причина отмены</h4>
                        <textarea disabled>{application.basedApplicationDto.solution}</textarea>
                    </div>
                </div>
            )
        if (application.basedApplicationDto.status === "Решена")
            return (
                <div>
                    <div className="application__buttons application__buttons-solo">
                        Заявка решена {getDatetimeToString(application.basedApplicationDto.solvedAt)}!
                    </div>
                    <div className="application__description">
                        <h4>Решение проблемы</h4>
                        <textarea disabled>{application.basedApplicationDto.solution}</textarea>
                    </div>
                </div>
            )

        if (context.user.role === "Работник") {
            return (
                <div className="application__buttons application__buttons-solo">
                    <DefaultModal
                        parModalText="Вы действительно хотите отозвать заявку? Для повторного обращения придется заново создать обращение."
                        buttonText="Отозвать заявку"
                        callback={() => ApplicationService.changeStatus(application, "Отменена")
                            .then((resp) => {
                                    window.location.reload(false)
                                }
                            )}
                        application={application}
                        newStatus="Отменена"
                    />
                </div>
            )
        }
        return getButtonByApplicationStatus(application)
    }


    if (application && !isLoading) {
        const targetUser = getPropsByUserRole({user: context.user, application});
        return (
            <div className="application__inner">
                <ApplicationPriorityIcon
                    color={getApplicationColorByPriority(application)}
                />
                <h2>
                    {application.category}
                </h2>
                <div className="application__info">
                    {application.category === "Заявка на технику"
                        ? <DeviceListItem
                            device={applicationObject}
                            children={getApplicationGeneralBlock()}
                        />
                        : <SoftwareListItem
                            software={applicationObject}
                            children={getApplicationGeneralBlock()}
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
    }
};

export default ApplicationIdPage;