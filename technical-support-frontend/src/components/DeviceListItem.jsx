import React from 'react';
import {DefaultButton} from "./UI/DefaultButton";
import {getApplicationCategoryImage} from "../utils/ApplicationUtils";

const DeviceListItem = ({device, children}) => {
    console.log(device)
    if (device) {
        return (
            <div className="device-list__item">
                <img src={process.env.PUBLIC_URL + `/img/${device.icon}.svg`} alt="Иконка"/>
                <div className="device-list__item-info">
                    <div className="device-list__item-title">
                        <b>{device.title}</b>
                    </div>
                    <div className="opacity-text">{device.id}</div>
                    <div>{device.type}</div>
                    <div>Выдано {device.issuedAt}</div>
                    {children}
                </div>
            </div>
        );
    }
};

export default DeviceListItem;