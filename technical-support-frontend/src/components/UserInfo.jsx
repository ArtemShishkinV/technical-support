import React, {useEffect, useState} from 'react';
import {AvatarImage} from "./UI/AvatarImage";
import "../css/UserInfo.css";
import {UserContactInfo} from "./UserContactInfo";
import {getPhoneNumber} from "../utils/PhoneUtils";
import {Collapse} from "antd";
import {DeviceList} from "./DeviceList";
import {useFetching} from "../hooks/UseFetching";
import DeviceService from "../API/DeviceService";
import {DefaultLoader} from "./UI/DefaultLoader";

export const UserInfo = ({user}) => {
    const {Panel} = Collapse;
    const [devices, setDevices] = useState([])

    const [fetchDevices, isLoading, error] = useFetching(async () => {
        const resp = await DeviceService.getByOwner(user)
        setDevices(resp.data)
    })

    useEffect(() => {
        fetchDevices()
    }, [])

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
                        </div>
                    </div>
                    <Collapse className="user-info__devices">
                        <Panel header="Закрепленные устройства" key="1">
                            {isLoading
                                ? <DefaultLoader/>
                                : <DeviceList devices={devices}/>
                            }
                        </Panel>
                    </Collapse>
                </div>
            </div>
        </div>
    );
};