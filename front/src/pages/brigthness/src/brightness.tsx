import './brightness.css';
import {LineChart} from "@mui/x-charts";
import {DataDisplay} from "../../../components/parts/data-display";
import {useEffect, useState} from "react";
import {BrightnessResponse} from "../../../types/back/brightness.type";
import {PowerButton} from "../../../components/power-button";
import {useSearchParams} from "react-router-dom";

export const Brightness = () => {
    const [brightnessLevels, setBrightnessLevels] = useState<BrightnessResponse[]>([]);
    const [powerStatus, setPowerStatus] = useState(false);

    const id = useSearchParams()[0].get('id');

    const changeStatus = () => {
        fetch(`${process.env.REACT_APP_API_HOST}/mqtt/Luminosity/toggle/${id}`, {
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
        fetch(`${process.env.REACT_APP_API_HOST}/sensors/status/Luminosity/${id}`, {
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
        fetch(`${process.env.REACT_APP_API_HOST}/luminosities/today/${id}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                response.json()
                    .then(data => setBrightnessLevels(data))
                    .catch(error => console.log(error));
            })
            .catch(error => console.error(error));
    }

    useEffect(() => {
        getStatus();
        updateData();
    }, []);

    return(
        <div className="brightness-page">
            <DataDisplay width="1000px" height="600px">
                <LineChart
                    dataset={brightnessLevels}
                    xAxis={[{scaleType: 'band', dataKey: 'time', label: 'Time'}]}
                    series={[{ dataKey: 'percentOfLuminosity', label: 'Brightness (%)'}]}
                    width={1000}
                    height={600}/>
            </DataDisplay>
            <PowerButton className="brightness-page-power-button" defaultState={powerStatus} onClick={changeStatus}/>
        </div>
    );
}

