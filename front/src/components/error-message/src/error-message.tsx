import './error-message.css';
import {ErrorMessageProps} from "./error-message.type";

export const ErrorMessage = ({...props}: ErrorMessageProps) => (
    <div className="error-message-container">
        <title className="error-message-title">
            <span className="error-message-icon"/>
            {props.title}
        </title>
        {props.message &&
            <span className="error-message-content">{props.message}</span>
        }
    </div>
);
