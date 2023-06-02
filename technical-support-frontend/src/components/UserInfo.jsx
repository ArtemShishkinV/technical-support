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
import UserInfoMain from "./UserInfoMain";
import {DefaultCollapse} from "./UI/DefaultCollapse";

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
                    <UserInfoMain user={user}/>
                    <DefaultCollapse title="Закрепленные устройства">
                        {isLoading
                            ? <DefaultLoader/>
                            : <DeviceList devices={devices}/>
                        }
                    </DefaultCollapse>
                </div>
            </div>
        </div>
    );
};