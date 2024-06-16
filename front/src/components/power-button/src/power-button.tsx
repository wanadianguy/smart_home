import './power-button.css';
import {useEffect, useState} from "react";
import {PowerButtonProps} from "./power-button.type";

export const PowerButton = ({...props}: PowerButtonProps) => {
    const [state, setState] = useState<boolean>(props.defaultState);

    useEffect(() => {
        setState(props.defaultState);
    }, [props.defaultState]);

    return (
        <button
            className={`switch-button-container ${props.className} ${state ? 'switch-button-container-on' : 'switch-button-container-off'}`}
            onClick={event => {
                props.onClick(event);
                setState(!state);
            }}>
            <span className={state ? "switch-button-on-icon" : "switch-button-off-icon"}/>
        </button>
    );
};
