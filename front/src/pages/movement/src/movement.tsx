import {LineChart} from "@mui/x-charts";
import {DataDisplay} from "../../../components/parts/data-display";
import "./movement.css";
import {InputNumber} from "../../../components/input-number";
import {useEffect, useState} from "react";
import {MovementResponse} from "../../../types/back/movements.type";
import {currentDate, getDateFromNumberOfDays} from "../../../utils/date-handler";
import {PowerButton} from "../../../components/power-button";
import {useSearchParams} from "react-router-dom";

const inputId = 'movement-input';

export const Movement = () => {
    const [movements, setMovements] = useState<MovementResponse[]>([]);
    const [numberOfDays, setNumberOfDays] = useState<number>(1);
    const [powerStatus, setPowerStatus] = useState(false);

    const id = useSearchParams()[0].get('id');

    const changeStatus = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/mqtt/Movement/toggle/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                response.json()
                    .then()
                    .catch(error => console.log(error));
            })
            .catch(error => console.error(error));
    }

    const getStatus = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/sensors/status/Movement/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                response.text()
                    .then(data => {
                        data === 'true' ? setPowerStatus(true) : setPowerStatus(false);
                    })
                    .catch(error => console.log(error));
            })
            .catch(error => console.error(error));
    }

    const updateData = () => {
        if (numberOfDays <= 1) {
            fetch(`${process.env.REACT_APP_API_HOST}/movements/today/${id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    response.json()
                        .then(data => setMovements(data ? data : []))
                        .catch(error => console.log(error));
                })
                .catch(error => console.error(error));
        } else {
            fetch(`${process.env.REACT_APP_API_HOST}/movements/${id}?startDate=${getDateFromNumberOfDays(numberOfDays)}&endDate=${currentDate()}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    response.json()
                        .then(data => setMovements(data ? data : []))
                        .catch(error => console.log(error));
                })
                .catch(error => console.error(error));
        }
    }

    useEffect(() => {
        getStatus();
        updateData();
    }, [numberOfDays]);

    return (
        <div className="movement-page">
            {numberOfDays <= 1
                ? <DataDisplay width="27rem" height="auto">
                    <ul className="movement-page-list">
                        All detections from today:
                        {movements.map(detection =>
                            <li key={JSON.stringify(detection.time)} className="movement-page-list-item">
                                {JSON.stringify(detection.time)}
                            </li>
                        )}
                    </ul>
                </DataDisplay>
                : <DataDisplay width="60rem" height="40rem">
                    <LineChart
                        dataset={movements}
                        xAxis={[{scaleType: 'band', dataKey: 'date', label: 'Date'}]}
                        series={[{dataKey: 'numberOfMovements', label: 'Number of detection'}]}
                        width={1000}
                        height={600}/>
                </DataDisplay>
            }
            <span className="movement-day-selector">
                Display the last
                <InputNumber id={inputId} defaultValue={numberOfDays} onChange={event => setNumberOfDays(Number(event.target.value))}/>
                days
            </span>
            <PowerButton className="movement-page-power-button" defaultState={powerStatus} onClick={changeStatus}/>
        </div>
    );
}
