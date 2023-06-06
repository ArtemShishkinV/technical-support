export const getDatetimeToString = (datetime) => {
    let d = new Date(datetime)
    return ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2)  + " " +
        ("0" + d.getDate()).slice(-2) + "-" + ("0"+(d.getMonth()+1)).slice(-2) + "-" + d.getFullYear()
};

export const sortDate = (date, anotherDate) => {
    return new Date(anotherDate) - new Date(date);
}
