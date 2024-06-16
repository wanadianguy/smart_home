import "./header.css";
import {HeaderProps} from "./header.type";

export const Header = ({...props}: HeaderProps) => (
    <div className="header-container">
        <button className="header-logo" onClick={() => window.location.href = window.origin}/>
        {props.title &&
            <title className="header-title">{props.title}</title>
        }
    </div>
);
