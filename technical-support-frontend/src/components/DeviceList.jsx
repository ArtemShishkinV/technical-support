import React from 'react';
import DeviceListItem from "./DeviceListItem";
import {DefaultButton} from "./UI/DefaultButton";

export const DeviceList = ({devices}) => {
    return (
        <div className="device-list">
            {devices.map((device) =>
                <DeviceListItem
                    device={device}
                    children={
                        <DefaultButton className="device-list__item-button">
                            Создать заявку
                        </DefaultButton>
                    }/>
            )}
        </div>
    );
};