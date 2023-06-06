import React from 'react';
import {AvatarImage} from "./UI/AvatarImage";
import {UserContactInfo} from "./UserContactInfo";
import {getPhoneNumber} from "../utils/PhoneUtils";
import UserPhoneMenu from "./UserPhoneMenu";

const UserInfoMain = ({user}) => {

    return (
        <div className="user-info__main-wrapper">
            <div className="user-info__main">
                <AvatarImage/>
                <div>
                    <div><b>{user.lastName} {user.firstName} {user.middleName}</b></div>
                    <div>{user.department.title}</div>
                    <div className="user-info__post">{user.post.title}</div>
                    <div>Табельный номер: {user.staffNumber}</div>
                    <div>{user.role}</div>
                </div>
            </div>
            <div className="user-info__contacts">
                <UserContactInfo
                    title={"Электронная почта"}
                    value={user.email}
                    img={"email"}
                />
                <div className="user-info__contact-item">
                    <div className="user-info__contacts-title">Телефон:</div>
                    <UserPhoneMenu
                        user={user}
                    />
                </div>
            </div>
            <div className="user-info__address">
                <div>
                    Адрес офиса:
                    г.{user.officeDto.city},
                    ул.{user.officeDto.street},
                    {user.officeDto.buildNumber}
                </div>
                <div className="user-info__workplace">
                    Рабочее место: {user.workplace.floor}-{user.workplace.roomNumber}-{user.workplace.tableNumber}
                </div>
            </div>
        </div>
    );
};

export default UserInfoMain;