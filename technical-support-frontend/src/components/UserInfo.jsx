import React from 'react';
import {AvatarImage} from "./UI/AvatarImage";
import "../css/UserInfo.css";
import {UserContactInfo} from "./UserContactInfo";
import {getPhoneNumber} from "../utils/PhoneUtils";

export const UserInfo = ({user}) => {
    return (
        <div >
            <div className="container">
                <div className="user-info__inner">
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
                        <UserContactInfo
                            title={"Номер телефона"}
                            value={getPhoneNumber(user.phoneNumber)}
                            img={"phone"}
                        />
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
                            {/*<div>Этаж: {user.workplace.floor}</div>*/}
                            {/*<div>Комната: {user.workplace.roomNumber}</div>*/}
                            {/*<div>Место: {user.workplace.tableNumber}</div>*/}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};