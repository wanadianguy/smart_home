import {MouseEventHandler} from "react";

export type TemperatureDisplayProps = {
    id: string;
    onClick?: MouseEventHandler<HTMLButtonElement>;
};

export type Variant = "very-low" | "low" | "medium" | "high" | "very-high" | "default";
