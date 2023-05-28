import React from 'react';

export const ApplicationPriorityIcon = ({priority}) => {
    const getColor = () => {
        if (priority === "Критический") return "#fa0202";
        if (priority === "Высокий") return "#FF8000";
        if (priority === "Низкий") return "#009900";
    }

    return (
        <div>
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path
                    d="M21.266 20.998H2.73301C2.37575 20.998 2.04563 20.8074 1.867 20.498C1.68837 20.1886 1.68838 19.8074 1.86701 19.498L11.133 3.49799C11.3118 3.1891 11.6416 2.9989 11.9985 2.9989C12.3554 2.9989 12.6852 3.1891 12.864 3.49799L22.13 19.498C22.3085 19.8072 22.3086 20.1882 22.1303 20.4975C21.9519 20.8069 21.6221 20.9976 21.265 20.998H21.266ZM11 15.998V17.998H11.933H11.998H12.063H12.998V15.998H11ZM11 8.99799V13.998H13V8.99799H11Z"
                    fill={getColor()}
                />
            </svg>
        </div>
    );
};