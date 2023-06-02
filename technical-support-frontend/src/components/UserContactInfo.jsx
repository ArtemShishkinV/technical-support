import React from 'react';

export const UserContactInfo = ({title, value, img}) => {
    return (
        <div className="user-info__contact-item">
            <div className="user-info__contacts-title">
                {title}:
            </div>
            <div className="user-info__contacts-value">
                <img src={process.env.PUBLIC_URL + `/img/${img}.svg`}/>
                {value}
            </div>
        </div>
    );
};