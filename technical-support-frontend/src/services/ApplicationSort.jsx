import {sortDate} from "../utils/DatetimeUtils";
import applications from "../pages/Applications";
import app from "../App";

const priorities = ["Низкий", "Высокий", "Критический"]

export const sortApplications = (applications, sortType) => {
    if (sortType === "По умолчанию")
        return [...applications].sort((a, b) => a.basedApplicationDto["id"] - b.basedApplicationDto["id"])
    if (sortType === "Сначала важные")
        return [...applications].sort((a, b) => sortByPriorities(a, b))
    if (sortType === "Сначала старые")
        return [...applications].sort((a, b) => sortByOlder(a, b))
    if (sortType === "Сначала новые")
        return [...applications].sort((a, b) => sortByNew(a, b))
    return applications
}

export const searchApplications = (applications, query) => {
    const lowQuery = query.toLowerCase()
    return applications.filter(application => application.category.toLowerCase().includes(lowQuery) ||
        application.applicationObjectDto.title.toLowerCase().includes(lowQuery) ||
        application.applicationObjectDto.category.toLowerCase().includes(lowQuery)
    )
}

const sortByPriorities = (application, anotherApplication) => {
    let result = priorities.indexOf(anotherApplication.basedApplicationDto["priority"])
        - priorities.indexOf(application.basedApplicationDto["priority"]);
    if (result === 0) result = sortByOlder(application, anotherApplication);
    return result;
}

const sortByOlder = (application, anotherApplication) => {
    return sortDate(anotherApplication.basedApplicationDto["createdAt"], application.basedApplicationDto["createdAt"]);
}

const sortByNew = (application, anotherApplication) => {
    return sortDate(application.basedApplicationDto["createdAt"], anotherApplication.basedApplicationDto["createdAt"]);
}