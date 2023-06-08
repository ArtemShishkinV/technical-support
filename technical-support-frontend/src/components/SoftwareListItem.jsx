import React from 'react';

export const SoftwareListItem = ({software, children}) => {
    console.log(software)
    if (software) {
        return (
            <div className="device-list__item">
                <img src={process.env.PUBLIC_URL + `/img/excel.png`} alt="Иконка"/>
                <div className="device-list__item-info">
                    <div className="device-list__item-title">
                        <b>{software.title}</b>
                    </div>
                    <div className="opacity-text">{software.category}</div>
                    {children}
                </div>
            </div>
        );
    }
};