import React from 'react';
import "../../css/Button.css";

export const DefaultButton = ({children, ...props}) => {
    return (
        <button {...props} className="myBtn">
            {children}
        </button>
    );
};