import React from 'react';
import "../css/CreateApplicationResult.css";

export const CreateApplicationResult = ({img_name, text, links}) => {
    return (
        <div>
            <div className="container">
                <div className="create-application-result__inner">
                    <div className="create-application-result__inner-main">
                        <img src={process.env.PUBLIC_URL + `/img/${img_name}.svg`} alt=""/>
                        <div>{text}</div>
                    </div>
                    {links}
                </div>
            </div>
        </div>
    );
};