export const currentDate = () => {
    const date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    let stringDay: string;
    let stringMonth: string;

    day < 10 ? stringDay = `0${day}` : stringDay = `${day}`;
    month < 10 ? stringMonth = `0${month}` : stringMonth = `${month}`;

    return `${year}-${stringMonth}-${stringDay}`;
};

export const getDateFromNumberOfDays = (numberOfDays: number) => {
    const date = new Date();
    date.setDate(date.getDate() - numberOfDays)
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    let stringDay: string;
    let stringMonth: string;

    day < 10 ? stringDay = `0${day}` : stringDay = `${day}`;
    month < 10 ? stringMonth = `0${month}` : stringMonth = `${month}`;

    return `${year}-${stringMonth}-${stringDay}`;
};
