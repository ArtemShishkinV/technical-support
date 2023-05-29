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
                <option value={defaultValue.title} disabled selected>{defaultValue.title}</option>
                {options.map(option=>
                    <option key={option.id} value={option.title}>
                        {option.title}
                    </option>
                )}
            </select>
        </div>

    );
};