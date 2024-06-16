import {TemperatureDisplayProps} from "./temperature-display.type";
import "./temperature-display.css";
import {DataDisplay} from "../../parts/data-display";
import {color, variant} from "./temperature-display.utils";
import {useEffect, useState} from "react";

export const TemperatureDisplay = ({...props}: TemperatureDisplayProps) => {
    const [currentTemperature, setCurrentTemperature] = useState<number>(0);
    const currentVariant = variant(currentTemperature);

    const getData = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/temperatures/current/${props.id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                response.json()
                    .then(data => setCurrentTemperature(data.degree))
                    .catch(error => console.log(error));
            })
            .catch(error => console.error(error));
    }

    useEffect(() => {
        getData();
        setInterval(getData, 10000);
    }, []);

    return(
        <DataDisplay width="25rem" height="17rem" onClick={props.onClick ?? undefined}>
            <title className="temperature-display-title">{`Sensor: ${props.id}`}</title>
            <span className="temperature-display-data" style={{color: color[currentVariant]}}>
                {currentTemperature} °C
                <span className={`${currentVariant}-temperature-display-icon`}/>
            </span>
        </DataDisplay>
    );
};
