import React from 'react';
import {Collapse} from "antd";
import "../../css/Collapse.css"

export const DefaultCollapse = ({children, title, ...props}) => {
    const {Panel} = Collapse;

    return (
        <Collapse {...props} className="myCollapse">
            <Panel header={title} key="1">
                {children}
            </Panel>
        </Collapse>
    );
};