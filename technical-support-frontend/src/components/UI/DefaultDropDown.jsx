import React from 'react';
import {Dropdown} from "antd";
import "../../css/Dropdown.css";

export const DefaultDropDown = ({items, children}) => {
    return (
        <Dropdown
            menu={{items}}
            trigger={['click']}
            placement={"bottomRight"}
            className="myDropdown"
        >
            {children}
        </Dropdown>
    );
};