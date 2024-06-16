import './brightness-display.css';
import {DataDisplay} from "../../parts/data-display";
import {BrightnessDisplayProps} from "./brightness-display.type";
import {useEffect, useState} from "react";

export const BrightnessDisplay = ({...props}: BrightnessDisplayProps) => {
    const [currentBrightness, setCurrentBrightness] = useState<number>(0);

    const getData = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/luminosities/current/${props.id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                } else {
                    response?.json()
                        .then(data => setCurrentBrightness(data.percentOfLuminosity))
                        .catch(error => console.log(error));
                }
            })
            .catch(error => console.error(error));
    }

    useEffect(() => {
        getData();
        setInterval(getData, 10000);
    }, []);

    return (
        <DataDisplay
            style={{backgroundColor: `rgb(${255 * currentBrightness}, ${255 * currentBrightness}, ${255 * currentBrightness})`}}
            width="27rem"
            height="15rem"
            onClick={props.onClick ?? undefined}>
            <title
                style={{color: currentBrightness <= 0.75 ? "white" : "black"}}
                className="brightness-display-title">
                {`Sensor: ${props.id}`}
            </title>
            <span className="brightness-display-data" style={{color: currentBrightness <= 0.75 ? "white" : "black"}}>
                {Math.trunc(currentBrightness * 100)} %
                <span
                    className={currentBrightness <= 0.75 ? "brightness-display-data-white-icon" : "brightness-display-data-black-icon"}/>
            </span>
        </DataDisplay>
    );
}
