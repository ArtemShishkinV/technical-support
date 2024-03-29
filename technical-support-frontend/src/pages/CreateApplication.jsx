import React, {useEffect, useMemo, useState} from 'react';
import "../css/CreateApplication.css";
import {DefaultSelect} from "../components/UI/DefaultSelect";
import CreateApplicationService from "../API/CreateApplicationService";
import {useFetching} from "../hooks/UseFetching";
import {LimitedTextarea} from "../components/UI/LimitedTextArea";
import {DefaultButton} from "../components/UI/DefaultButton";
import {IdSelect} from "../components/UI/IdSelect";
import {applicationCategories, getApplicationCategoryUrl, getObjectsByCategory} from "../utils/ApplicationUtils";
import {useHistory} from "react-router-dom";
import {DefaultInput} from "../components/UI/DefaultInput";
import {DefaultLoader} from "../components/UI/DefaultLoader";
import {AuthService} from "../API/AuthService";

export const CreateApplication = () => {
        const context = AuthService.isAuthenticated()
        const navigate = useHistory();

        const [application, updateApplication] = useState([
            {
                category: "",
                applicationObjectCategory: "",
                applicationObjectId: "",
                type: "",
                description: "",
                priority: "",
                isOffline: false
            }
        ])

        const [model, setModel] = useState([
            {id: 0, title: "Выберите приоритет"}
        ])

        const [types, setTypes] = useState([
            {id: 0, title: "Выберите тип"}
        ])

        const [applicationObjectCategory, setApplicationObjectCategory] = useState([
            {id: 0, title: "Выберите тип объекта"}
        ]);

        const [applicationObject, setApplicationObject] = useState([
            {id: 0, title: "Выберите объект"}
        ]);

        const [fetchModels, isModelsLoading, error] = useFetching(async () => {
            const response = await CreateApplicationService.getAllModels(context.user);
            setModel(response.data)
            console.log(response.data)
        })

        useEffect(() => {
            fetchModels()
        }, [])

        useMemo(() => {
            const tempTypes = [types[0]]
            const tempCategories = [applicationObjectCategory[0]]
            if (application.category === "Заявка на технику") {
                setTypes([...tempTypes, ...model.applicationDeviceTypes])
                setApplicationObjectCategory([...tempCategories, ...model.deviceTypes])
            }
            if (application.category === "Заявка на ПО") {
                setTypes([...tempTypes, ...model.applicationSoftwareTypes])
                setApplicationObjectCategory([...tempCategories, ...model.softwareTypes])
            }
        }, [application.category])

        useMemo(() => {
            const tempObjects = [applicationObject[0]]
            let devices = []
            if (application.category === "Заявка на технику") {
                if (application.type === "Заказать") {
                    devices = getObjectsByCategory(model.availableDevices, application.applicationObjectCategory)
                    setApplicationObject([...tempObjects, ...devices])
                } else {
                    devices = getObjectsByCategory(model.myDevices, application.applicationObjectCategory)
                    setApplicationObject([...tempObjects, ...devices])
                }
                console.log(devices)
            }
            if (application.category === "Заявка на ПО") {
                const software = getObjectsByCategory(model.softwares, application.applicationObjectCategory)
                setApplicationObject([...tempObjects, ...software])
                console.log(software)
            }
        }, [application.type, application.applicationObjectCategory, application.category])


        function createApplication() {
            const response = CreateApplicationService.create({
                initiator: context.user,
                category: application.category,
                applicationObjectId: application.applicationObjectId,
                type: application.type,
                description: application.description,
                priority: application.priority,
                isOffline: application.isOffline
            }).then((resp) => {
                navigate.push(`/create-application/success/${getApplicationCategoryUrl(resp.data.category).url}/${resp.data.basedApplicationDto.id}`)
            }).catch(() =>
                navigate.push("/create-application/failed")
            )
        }

        const mainPage = () => {
            return (
                <div className="create-application__main">
                    <DefaultSelect
                        options={applicationCategories.slice(1)}
                        value={application.category}
                        defaultValue={applicationCategories[0]}
                        onChange={event =>
                            updateApplication({...application, category: event})
                        }
                    />
                    <DefaultSelect
                        options={types.slice(1)}
                        value={application.type}
                        defaultValue={types[0]}
                        onChange={event => updateApplication({...application, type: event})}
                    />
                    <DefaultSelect
                        options={model.priorities}
                        value={application.priority}
                        defaultValue={{id: 0, title: "Выберите приоритет"}}
                        onChange={event => updateApplication({...application, priority: event})}
                    />
                    <DefaultSelect
                        options={applicationObjectCategory.slice(1)}
                        value={application.applicationObjectCategory}
                        defaultValue={applicationObjectCategory[0]}
                        onChange={event => updateApplication({...application, applicationObjectCategory: event})}
                    />
                    <IdSelect
                        options={applicationObject.slice(1)}
                        value={application.applicationObjectId}
                        defaultValue={applicationObject[0]}
                        onChange={event => updateApplication({...application, applicationObjectId: event})}
                    />
                    <div className="create-application__offline">
                        <div className="create-application__offline-title">Оффлайн решение</div>
                        <DefaultInput
                            type="checkbox"
                            onChange={event => updateApplication({...application, isOffline: event.target.checked})}
                        />
                    </div>
                    <LimitedTextarea
                        value={application.description}
                        limit={600}
                        setValue={event => updateApplication({...application, description: event})}
                    />
                    <DefaultButton onClick={createApplication}>
                        Создать заявку
                    </DefaultButton>
                </div>
            )
        }

        return (
            <div className="create-application">
                <div className="container">
                    <div className="create-application__inner">
                        <h1>Создание заявки</h1>
                        {isModelsLoading
                            ? <DefaultLoader/>
                            : mainPage()
                        }
                    </div>
                </div>
            </div>
        );
    }
;