import {LineChart} from "@mui/x-charts";
import {DataDisplay} from "../../../components/parts/data-display";
import "./temperature.css";
import {InputNumber} from "../../../components/input-number";
import {useEffect, useState} from "react";
import {TemperatureResponse} from "../../../types/back/temperatures.type";
import {currentDate, getDateFromNumberOfDays} from "../../../utils/date-handler";
import {PowerButton} from "../../../components/power-button";
import {useSearchParams} from "react-router-dom";

const inputId = 'temperature-input';

export const Temperature = () => {
    const [temperatures, setTemperatures] = useState<TemperatureResponse[]>([]);
    const [numberOfDays, setNumberOfDays] = useState<number>(1);
    const [powerStatus, setPowerStatus] = useState(false);

    const id = useSearchParams()[0].get('id');

    const changeStatus = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/mqtt/Temperature/toggle/${id}`, {
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
        fetch(`${process.env.REACT_APP_API_HOST}/sensors/status/Temperature/${id}`, {
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
            fetch(`${process.env.REACT_APP_API_HOST}/temperatures/today/${id}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    response.json()
                        .then(data => setTemperatures(data ? data : []))
                        .catch(error => console.log(error));
                })
                .catch(error => console.error(error));
        } else {
            fetch(`${process.env.REACT_APP_API_HOST}/averages-temperatures/${id}?startDate=${getDateFromNumberOfDays(numberOfDays)}&endDate=${currentDate()}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            })
                .then(response => {
                    response.json()
                        .then(data => setTemperatures(data ? data : []))
                        .catch(error => console.log(error));
                })
                .catch(error => console.error(error));
        }
    }

    useEffect(() => {
        getStatus();
        updateData();
    }, [numberOfDays]);

    return(
        <div className="temperature-page">
            <DataDisplay width="1000px" height="600px">
                <LineChart
                    dataset={temperatures}
                    xAxis={
                        numberOfDays <= 1
                        ? [{scaleType: 'band', dataKey: 'time', label: 'Time'}]
                        : [{scaleType: 'band', dataKey: 'date', label: 'Date'}]
                    }
                    series={
                        numberOfDays <= 1
                            ? [{ dataKey: 'degree', label: 'Temperature (°C)'}]
                            : [{ dataKey: 'averageDegree', label: 'Temperature (°C)'}]
                    }
                    width={1000}
                    height={600}/>
            </DataDisplay>
            <span className="temperature-day-selector">
                Display the last
                <InputNumber id={inputId} defaultValue={numberOfDays} onChange={event => setNumberOfDays(Number(event.target.value))}/>
                days
            </span>
            <PowerButton className="temperature-page-power-button" defaultState={powerStatus} onClick={changeStatus}/>
        </div>
    );
}
