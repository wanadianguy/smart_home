import {DataDisplay} from "../../parts/data-display";
import {MovementDisplayProps} from "./movement-display.type";
import {movementMessage} from "./movement-display.const";
import "./movement-display.css";
import {useEffect, useState} from "react";

export const MovementDisplay = ({...props}: MovementDisplayProps) => {
    const [currentMovementCount, setCurrentMovementCount] = useState<number>(0);

    const getData = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/movements/today/${props.id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                response.json()
                    .then(data => setCurrentMovementCount(data.length))
                    .catch(error => console.log(error));
            })
            .catch(error => console.error(error));
    }

    useEffect(() => {
        getData();
        setInterval(getData, 10000);
    }, []);

    return (
        <DataDisplay width="32rem" height="10rem" onClick={props.onClick}>
            <title className="movement-display-title">{`Sensor: ${props.id}`}</title>
            {currentMovementCount > 0 &&
                <span className="movement-display-data">{currentMovementCount}</span>
            }
            <span className="movement-display-text">{movementMessage(currentMovementCount)}</span>
        </DataDisplay>
    )
};
