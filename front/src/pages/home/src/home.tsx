import {TemperatureDisplay} from "../../../components/temperature-display";
import "./home.css";
import {MovementDisplay} from "../../../components/movement-display";
import {BrightnessDisplay} from "../../../components/brightness-display";
import {useEffect, useState} from "react";
import {SensorResponse} from "../../../types/back/sensors.type";
import {ErrorMessage} from "../../../components/error-message";
import {ERROR_MESSAGE, ERROR_TITLE} from "./home.const";

export const Home = () => {
    const [sensorList, setSensorList] = useState<SensorResponse[]>([]);

    const getDisplay = (id: string, type: string) => {
        switch (type) {
            case "Temperature":
                return <TemperatureDisplay id={id} onClick={() => window.location.href = `${window.origin}/temperature?id=${id}`}/>;
            case "Movement":
                return <MovementDisplay id={id} onClick={() => window.location.href = `${window.origin}/movement?id=${id}`}/>;
            case "Luminosity":
                return <BrightnessDisplay id={id} onClick={() => window.location.href = `${window.origin}/brightness?id=${id}`}/>;
            default:
                return <></>;
        }
    };

    const getAllSensors = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/sensors/all`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                response.json()
                    .then(data => setSensorList(data))
                    .catch(error => console.log(error));
            })
            .catch(error => console.error(error));
    }

    useEffect(() => {
        getAllSensors()
        setInterval(getAllSensors, 10000);
    }, []);

    return (
        <div className="home-page">
            {sensorList.length > 0
                ? <ul className="home-page-list">
                    {
                        sensorList.map(sensor => sensor.state && (
                            <li className="home-page-list-item" key={sensor.sensorId}>
                                {getDisplay(sensor.sensorId, sensor.type)}
                            </li>
                        ))
                    }
                </ul>
                : <ErrorMessage title={ERROR_TITLE} message={ERROR_MESSAGE}/>
            }
            <MovementDisplay id="Movement-esp32-002" onClick={() => window.location.href = `${window.origin}/movement?id=Movement-esp32-002`}/>
        </div>
    );
}

