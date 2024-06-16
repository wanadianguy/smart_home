import {MouseEventHandler} from "react";

export type PowerButtonProps = {
    className?: string;
    defaultState: boolean;
    onClick: MouseEventHandler<HTMLButtonElement>;
};
