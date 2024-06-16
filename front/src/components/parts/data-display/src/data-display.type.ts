import {CSSProperties, MouseEventHandler} from "react";

export type DataDisplayProps = {
    width: string;
    height: string;
    className?: string;
    style?: CSSProperties;
    onClick?: MouseEventHandler<HTMLButtonElement>;
};
