import React from 'react';
import "../../css/Select.css"

export const IdSelect = ({options, defaultValue, value, onChange}) => {
    return (
        <div>
            {(typeof options !== "undefined") ?
                <div>
                    <select
                        className="mySelect"
                        value={value}
                        onChange={event => onChange(event.target.value)}
                    >
                        <option value={defaultValue.title} disabled selected>{defaultValue.title}</option>
                        {options.map(option =>
                            <option key={option.id} value={option.id}>
                                {option.title}
                            </option>
                        )}
                    </select>
                </div>
                : <h2></h2>
            }
        </div>

    );
};