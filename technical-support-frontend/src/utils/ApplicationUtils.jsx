import React from "react";
import {DefaultModal} from "../components/DefaultModal";
import ApplicationService from "../API/ApplicationService";
import {LimitedTextarea} from "../components/UI/LimitedTextArea";
import {ApplicationPriorityIcon} from "../components/UI/ApplicationPriorityIcon";

// const getCallbackByApplicationType = (application) => {
//     if (application.category === "Заявка на ПО")
//         return () => ApplicationService.changeStatus(application, "Решена")
//             .then((resp) => {
//                     window.location.reload(false)
//                 }
//             )
//
//     const device = {
//         id: application.applicationObjectDto.id,
//         title: "",
//         description: "",
//         type: "",
//         icon: "",
//         issuedAt: ""
//     }
//
//     if (application.type === "Ремонт") {
//         return () => {
//             DeviceService.updateCondition()
//             ApplicationService.changeStatus(application, "Решена")
//                 .then((resp) => {
//                         window.location.reload(false)
//                     }
//                 )
//         }
//     }
// }

const solutionModalText = (application) => {
    console.log(application)
    return <div className="ant-modal-body__inner">
        <h4>Кратко опишите решение проблемы</h4>
        <LimitedTextarea
            value={application.basedApplicationDto.solution}
            limit={600}
            setValue={(value) => application.basedApplicationDto.solution = value}
        />
    </div>
}

export const applicationCategories = [
    {id: 0, title: "Выберите категорию", url: ""},
    {id: 1, title: "Заявка на технику", url: "device"},
    {id: 2, title: "Заявка на ПО", url: "software"}
]

export const getButtonByApplicationStatus = (application) => {
    if (application.basedApplicationDto.status === "Создана")
        return (
            <div className="application__buttons application__buttons-solo">
                <DefaultModal
                    title="Вы подтверждаете, что берете заявку в работу?"
                    buttonText="Взять в работу"
                    callback={() => ApplicationService.changeStatus(application, "В работе")
                        .then((resp) => {
                                window.location.reload(false)
                            }
                        )}
                    application={application}
                    newStatus="Отменена"
                />
            </div>
        )
    if (application.basedApplicationDto.status === "В работе")
        return (
            <div className="application__buttons">
                <DefaultModal
                    parModalText={solutionModalText(application)}
                    buttonText="Отправить решение"
                    title="Закрытие заявки"
                    callback={() => ApplicationService.changeStatus(application, "Решена")
                        .then((resp) => {
                            window.location.reload(false)
                        })}
                    application={application}
                    newStatus="Решена"
                />
                <DefaultModal
                    parModalText={solutionModalText(application)}
                    buttonText="Отменить заявку"
                    title="Вы действительно хотите отменить заявку?"
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

export const getApplicationCategoryUrl = (title) => {
    return applicationCategories.filter(item => item.title === title).pop()
}

export const getApplicationCategoryTitle = (url) => {
    return applicationCategories.filter(item => item.url === url).pop()
}

export const getApplicationCategoryImage = (application) => {
    if (application.category === applicationCategories[1].title)
        return <ApplicationPriorityIcon
            color={getApplicationColorByPriority(application.basedApplicationDto.priority)}/>
    return <img src={process.env.PUBLIC_URL + `/img/software.png`}/>
}

export const getObjectsByCategory = (objects, category) => {
    return objects.filter(item => item.category === category);
}

export const getPropsByUserRole = ({user, application}) => {
    if (application.basedApplicationDto.initiator.staffNumber === user.staffNumber) {
        return {
            application,
            userTitle: "Исполнитель",
            collapseTitle: "Об исполнителе",
            applicationUser: application.basedApplicationDto.executor
        }
    }
    return {
        application,
        userTitle: "Клиент",
        collapseTitle: "О клиенте",
        applicationUser: application.basedApplicationDto.initiator
    }
}

export const getApplicationColorByPriority = (application) => {
    if (application.basedApplicationDto.priority === "Критический") return "#fa0202";
    if (application.basedApplicationDto.priority === "Высокий") return "#FF8000";
    if (application.basedApplicationDto.priority === "Низкий") return "#009900";
}
