export type SensorResponse = {
    sensorId: string;
    type: SensorType;
    state: boolean;
};

type SensorType = "Luminosity" | "Movement" | "Temperature";
