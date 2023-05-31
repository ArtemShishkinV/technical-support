import React from 'react';
import {DefaultButton} from "./UI/DefaultButton";

const DeviceListItem = ({device}) => {
    console.log(device)
    return (
        <div className="device-list__item">
            <img src={process.env.PUBLIC_URL + `/img/${device.icon}.svg`} alt="Иконка"/>
            <div className="device-list__item-info">
                <div className="device-list__item-title">
                    <b>{device.title}</b>
                </div>
                <div className="opacity-text">{device.id}</div>
                <div>{device.type}</div>
                {/*<div>{device.description}</div>*/}
                {/*<div>{device.id}</div>*/}
                <div>Выдано {device.issuedAt}</div>
                <DefaultButton className="device-list__item-button">
                    Отвязать
                </DefaultButton>
            </div>
        </div>
    );
};

export default DeviceListItem;