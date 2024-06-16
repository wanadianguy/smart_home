import {Variant} from "./temperature-display.type";

export const color = {
    "very-low": "#19a8db",
    "low": "#2a5fac",
    "medium": "#f7970a",
    "high": "#ec5c11",
    "very-high": "#d2331b",
    "default": "black"
};

export const variant = (temperature: number) : Variant => {
    if (temperature <= 5) return "very-low";
    if (temperature > 5 && temperature <= 14) return "low";
    if (temperature > 14 && temperature <= 21) return "medium";
    if (temperature > 21 && temperature <= 28) return "high";
    if(temperature > 28) return "very-high";
    return "default";
}
