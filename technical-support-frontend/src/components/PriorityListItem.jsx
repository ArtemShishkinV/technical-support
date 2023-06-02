import React from 'react';
import {Switch} from "antd";
import "../css/Priority.css";

const PriorityListItem = ({priority}) => {
    return (
        <div className="priority-list-item">
            <div>{priority.title}</div>
            <Switch defaultChecked/>
        </div>
    );
};

export default PriorityListItem;