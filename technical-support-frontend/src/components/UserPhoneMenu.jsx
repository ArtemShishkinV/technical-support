import React from 'react';
import {DefaultDropDown} from "./UI/DefaultDropDown";
import {DefaultButton} from "./UI/DefaultButton";
import {getPhoneNumber} from "../utils/PhoneUtils";

const UserPhoneMenu = ({user}) => {
    console.log(user)
    const phoneLink = `tel:+${user.phoneNumber}`
    const tgLink = `https://t.me/+${user.phoneNumber}`

    const items = [
        {
            key: '1',
            label: (
                <a target="_blank" rel="noopener noreferrer" href={phoneLink}>
                    Позвонить
                </a>
            ),
        },
        {
            key: '2',
            label: (
                <a target="_blank" rel="noopener noreferrer" href={tgLink}>
                    Написать в ТГ
                </a>
            ),
        }
    ]

    return (
        <DefaultDropDown
            items={items}
            children={
                <DefaultButton>
                    <img src={process.env.PUBLIC_URL + "/img/phone.svg"}/>
                    {getPhoneNumber(user.phoneNumber)}
                </DefaultButton>
            }
        />
    );
};

export default UserPhoneMenu;