import React from 'react';

export const UserContactInfo = ({title, value, img}) => {
    return (
        <div className="user-info__contact-item">
            <span className="user-info__contacts-title">
                {title}:
            </span>
            <span className="user-info__contacts-value">
                <img src={process.env.PUBLIC_URL + `/img/${img}.svg`}/>
                {value}
            </span>
        </div>
    );
};