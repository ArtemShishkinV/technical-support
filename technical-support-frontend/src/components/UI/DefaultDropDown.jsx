import React from 'react';
import {Dropdown} from "antd";

export const DefaultDropDown = ({items, children}) => {
    return (
        <Dropdown
            menu={{items}}
            trigger={['click']}
            placement={"bottomRight"}
        >
            {children}
        </Dropdown>
    );
};