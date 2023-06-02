import React, {useContext, useEffect, useState} from 'react';
import {useFetching} from "../hooks/UseFetching";
import DeviceService from "../API/DeviceService";
import {DefaultLoader} from "../components/UI/DefaultLoader";
import {DeviceList} from "../components/DeviceList";
import "../css/Devices.css";
import {AuthService} from "../API/AuthService";

const Devices = () => {
    const context = AuthService.isAuthenticated()

    const [devices, setDevices] = useState([])

    const [fetchDevices, isLoading, error] = useFetching(async () => {
        const resp = await DeviceService.getByOwner(context.user)
        setDevices(resp.data)
    })

    useEffect(() => {
        fetchDevices()
    }, [])

    return (
        <div className="devices-list__wrapper">
            <div className="container">
                <div className="device-list__inner">
                    <h1>Мои устройства</h1>
                    {isLoading
                        ? <DefaultLoader/>
                        : <DeviceList devices={devices}/>}
                </div>
            </div>
        </div>
    );
};

export default Devices;