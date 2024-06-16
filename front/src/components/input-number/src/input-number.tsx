import './input-number.css';
import {InputTextProps} from "./input-text.type";

export const InputNumber = ({...props}: InputTextProps) => (
    <input className="input-number-container" id={props.id} type="number" defaultValue={props.defaultValue} onChange={props.onChange}/>
);
