import {ChangeEventHandler} from "react";

export type InputTextProps = {
    id: string;
    defaultValue: number;
    onChange: ChangeEventHandler<HTMLInputElement>;
};
