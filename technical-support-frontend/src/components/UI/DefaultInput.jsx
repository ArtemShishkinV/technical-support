import React from 'react';
import "../../css/Input.css";

export const DefaultInput = React.forwardRef((props, ref) => {
    return (
        <input ref={ref} className="myInput" {...props}/>
    );
});