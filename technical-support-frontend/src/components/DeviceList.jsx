import React from 'react';
import DeviceListItem from "./DeviceListItem";

export const DeviceList = ({devices}) => {
    return (
        <div className="device-list">
            {devices.map((device) =>
                <DeviceListItem device={device}/>
            )}
        </div>
    );
};