import React from 'react';
import PriorityListItem from "./PriorityListItem";

export const PriorityList = ({priorities}) => {
    return (
        <div className="priority-list">
            {priorities.map((priority) =>
                <PriorityListItem
                    priority={priority}/>
            )}
        </div>
    );
};