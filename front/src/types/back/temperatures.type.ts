export type TemperatureResponse = {
    date: string;
    averageDegree: number;
} | {
    date: string;
    time: string;
    degree: number;
};
