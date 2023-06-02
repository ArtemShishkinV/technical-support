import React from 'react';
import {DefaultDropDown} from "./UI/DefaultDropDown";
import {getPhoneNumber} from "../utils/PhoneUtils";
import "../css/Dropdown.css";

const UserPhoneMenu = ({user}) => {
    console.log(user)
    const phoneLink = `tel:+${user.phoneNumber}`
    const tgLink = `https://t.me/+${user.phoneNumber}`

    const items = [
        {
            key: '1',
            label: (
                <a className="myDropdown-link" target="_blank" rel="noopener noreferrer" href={phoneLink}>
                    Позвонить
                </a>
            ),
        },
        {
            key: '2',
            label: (
                <a className="myDropdown-link" target="_blank" rel="noopener noreferrer" href={tgLink}>
                    Написать в ТГ
                </a>
            ),
        }
    ]

    return (
        <DefaultDropDown
            items={items}
            children={
                <button className="user-info__contacts-dropdown-btn">
                    <img src={process.env.PUBLIC_URL + "/img/phone.svg"}/>
                    {getPhoneNumber(user.phoneNumber)}
                </button>
            }
        />
    );
};

export default UserPhoneMenu;