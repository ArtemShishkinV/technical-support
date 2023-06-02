import React, {useEffect, useState} from 'react';
import "../css/UserInfo.css";
import {Collapse} from "antd";
import {DeviceList} from "./DeviceList";
import {useFetching} from "../hooks/UseFetching";
import DeviceService from "../API/DeviceService";
import {DefaultLoader} from "./UI/DefaultLoader";
import UserInfoMain from "./UserInfoMain";
import {DefaultCollapse} from "./UI/DefaultCollapse";
import PriorityService from "../API/PriorityService";
import {PriorityList} from "./PriorityList";

export const UserInfo = ({user}) => {
    const {Panel} = Collapse;
    const [devices, setDevices] = useState([])
    const [priorities, setPriorities] = useState([])

    // const isProfile = window.location.pathname.includes("profile")

    const [fetchDevices, isLoading, _] = useFetching(async () => {
        const resp = await DeviceService.getByOwner(user)
        setDevices(resp.data)
    })

    const [fetchPriorities, isPrioritiesLoading, __] = useFetching(async () => {
        const resp = await PriorityService.getAll()
        setPriorities(resp.data)
    })

    const getCollapse = () => {
        // if (isProfile) {
            if (user.role === "Работник")
                return (
                    <DefaultCollapse title="Закрепленные устройства">
                        {isLoading
                            ? <DefaultLoader/>
                            : <DeviceList devices={devices}/>
                        }
                    </DefaultCollapse>
                )
            else
                return (
                    <DefaultCollapse title="Мои уведомления">
                        {isLoading
                            ? <DefaultLoader/>
                            : <PriorityList priorities={priorities}/>
                        }
                    </DefaultCollapse>
                )
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
                    {/*{isProfile*/}
                    {getCollapse()}
                    {/*//     : <div></div>*/}
                    {/*// }*/}
                </div>
            </div>
        </div>
    );
};