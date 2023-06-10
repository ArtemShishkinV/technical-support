import React, {useEffect, useState} from 'react';
import "../css/UserInfo.css";
import {DeviceList} from "./DeviceList";
import {useFetching} from "../hooks/UseFetching";
import DeviceService from "../API/DeviceService";
import {DefaultLoader} from "./UI/DefaultLoader";
import UserInfoMain from "./UserInfoMain";
import {DefaultCollapse} from "./UI/DefaultCollapse";
import PriorityService from "../API/PriorityService";
import {PriorityList} from "./PriorityList";

export const UserInfo = ({user}) => {
    const [devices, setDevices] = useState([])
    const [priorities, setPriorities] = useState([])

    const [fetchDevices, isLoading, _] = useFetching(async () => {
        const resp = await DeviceService.getByOwner(user)
        setDevices(resp.data)
    })

    const [fetchPriorities, ___, __] = useFetching(async () => {
        const resp = await PriorityService.getAll()
        setPriorities(resp.data)
    })

    const getCollapse = () => {
        if (user.role === "Администратор") return;
        if (user.role === "Работник")
            return (
                <DefaultCollapse title="Закрепленные устройства">
                    {isLoading
                        ? <DefaultLoader/>
                        : <DeviceList devices={devices}/>
                    }
                </DefaultCollapse>
            )
        if (user.activeBot) {
            return (
                <DefaultCollapse title="Мои уведомления">
                    {isLoading
                        ? <DefaultLoader/>
                        : <PriorityList priorities={priorities}/>
                    }
                </DefaultCollapse>
            )
        } else {
            return (
                <a className="user-info__bot-link" href="https://t.me/TechnicalSupportGraduateBot">
                    Кликни и пройди регистрацию в боте, чтобы получать уведомления!
                </a>
            )
        }
    }

    useEffect(() => {
        fetchDevices()
        fetchPriorities()
    }, [])

    return (
        <div>
            <div className="container">
                <div className="user-info__inner">
                    <UserInfoMain user={user}/>
                    {getCollapse()}
                </div>
            </div>
        </div>
    );
};