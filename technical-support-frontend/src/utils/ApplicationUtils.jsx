// export const getDevicesByApplicationType = (objects, type) => {
//     return objects.filter(item => item.category === type);
// }

export const applicationCategories = [
    {id: 0, title: "Выберите категорию", url: ""},
    {id: 1, title: "Заявка на технику", url: "device"},
    {id: 2, title: "Заявка на ПО", url: "software"}
]

export const getApplicationCategoryUrl = (title) => {
    return applicationCategories.filter(item => item.title === title).pop()
}

export const getApplicationCategoryTitle = (url) => {
    return applicationCategories.filter(item => item.url === url).pop()
}

export const getObjectsByCategory = (objects, category) => {
    return objects.filter(item => item.category === category);
}
