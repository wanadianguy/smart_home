import "./data-display.css";
import {DataDisplayProps} from "./data-display.type";
import {PropsWithChildren} from "react";

export const DataDisplay = ({...props}: PropsWithChildren<DataDisplayProps>) => (
    props.onClick
        ? <button
            className={`data-display-button-container ${props.className ?? ""}`}
            style={{width: props.width, height: props.height, ...props.style}}
            onClick={props.onClick}>
            {props.children}
        </button>
        : <div
            className={`data-display-container ${props.className}`}
            style={{width: props.width, height: props.height, ...props.style}}>
            {props.children}
        </div>
);
