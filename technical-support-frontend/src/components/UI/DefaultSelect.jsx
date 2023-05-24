import React from 'react';
import "../../css/Select.css"

export const DefaultSelect = ({label, options, defaultValue, value, onChange}) => {
    return (
        <div>
            <label htmlFor="select">{label}</label>
            <select
                className="mySelect"
                value={value}
                onChange={event => onChange(event.target.value)}
            >
                <option value={defaultValue.value} selected>{defaultValue.name}</option>
                {options.map(option=>
                    <option key={option.value} value={option.value}>
                        {option.name}
                    </option>
                )}
            </select>
        </div>

    );
};