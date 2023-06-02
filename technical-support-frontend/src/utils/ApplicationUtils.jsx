import {DefaultButton} from "../components/UI/DefaultButton";

export const applicationCategories = [
    {id: 0, title: "Выберите категорию", url: ""},
    {id: 1, title: "Заявка на технику", url: "device"},
    {id: 2, title: "Заявка на ПО", url: "software"}
]

export const getButtonByApplicationStatus = (status) => {
    if (status === "Создана")
        return (
            <div className="application__buttons application__buttons-solo">
                <DefaultButton>Взять в работу</DefaultButton>
            </div>
        )
    if (status === "В работе")
        return (
            <div className="application__buttons">
                <DefaultButton>Решить</DefaultButton>
                <DefaultButton>Отменить</DefaultButton>
            </div>
        )
}

export const getApplicationCategoryUrl = (title) => {
    return applicationCategories.filter(item => item.title === title).pop()
}

export const getApplicationCategoryTitle = (url) => {
    return applicationCategories.filter(item => item.url === url).pop()
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

// export async function getApplicationObject(application) {
//     if (application.category === "Заявка на технику") {
//         return await
//     }
// }