import {sortDate} from "../utils/DatetimeUtils";

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