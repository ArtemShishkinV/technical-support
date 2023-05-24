import React from 'react';
import {DefaultInput} from "./UI/DefaultInput";
import {DefaultSelect} from "./UI/DefaultSelect";
import "../css/Filter.css";

export const DefaultFilter = ({options, filter, setFilter}) => {
    return (
        <div className="myFilter">
            <DefaultInput
                value={filter.query}
                onChange={event=>setFilter({...filter, query: event.target.value})}
                placeholder="Поиск..."
            />
            <DefaultSelect
                // label="Сортировка "
                value={filter.sort}
                options={options.slice(1)}
                onChange={event => setFilter({...filter, sort: event})}
                defaultValue={options[0]}
            />
        </div>
    );
};