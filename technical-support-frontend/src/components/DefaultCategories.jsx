import React from 'react';
import {DefaultButton} from "./UI/DefaultButton";
import "../css/Categories.css";

export const DefaultCategories = ({categories}) => {
    return (
        <div className="myCategories">
            {categories.map(category =>
                <DefaultButton onClick={category.callback} key={category.name}>{category.name}</DefaultButton>
            )}
        </div>
    );
};