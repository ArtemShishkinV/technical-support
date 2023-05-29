import React, {useContext, useEffect, useMemo, useState} from 'react';
import "../css/CreateApplication.css";
import {DefaultSelect} from "../components/UI/DefaultSelect";
import {AppContext} from "../AppContext";
import CreateApplicationService from "../API/CreateApplicationService";
import {useFetching} from "../hooks/UseFetching";
import {LimitedTextarea} from "../components/LimitedTextArea";
import {DefaultButton} from "../components/UI/DefaultButton";

export const CreateApplication = () => {
        const categories = [
            {id: 0, title: "Выберите категорию"},
            {id: 1, title: "Заявка на технику"},
            {id: 2, title: "Заявка на ПО"}
        ]
        const user = useContext(AppContext);

        const [application, updateApplication] = useState([
            {
                category: "",
                applicationObjectCategory: "",
                applicationObjectTitle: "",
                type: "",
                description: "fdsf",
                initiator: user,
                executor: null,
                priority: "",
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
            const response = await CreateApplicationService.getAllModels(user);
            setModel(response.data)
        })

        useEffect(() => {
            fetchModels()
            console.log(model)
        }, [])

        useMemo(() => {
            const tempTypes = [types[0]]
            const tempCategories = [applicationObjectCategory[0]]
            const tempObjects = [applicationObject[0]]
            console.log(application)
            if (application.category === "Заявка на технику") {
                setTypes([...tempTypes, ...model.applicationDeviceTypes])
                setApplicationObjectCategory([...tempCategories, ...model.deviceTypes])
                setApplicationObject([...tempObjects, ...model.availableDevices])
            }
            if (application.category === "Заявка на ПО") {
                setTypes([...tempTypes, ...model.applicationSoftwareTypes])
                setApplicationObjectCategory([...tempCategories, ...model.softwareTypes])
                setApplicationObject([...tempObjects, ...model.softwares])
            }
        }, [application.category])



        function createApplication() {
            console.log(application)

            // CreateApplicationService(application)
        }

        return (
            <div className="create-application">
                <div className="container">
                    <div className="create-application__inner">
                        <h1>Создание заявки</h1>
                        <div className="create-application__main">
                            <DefaultSelect
                                options={categories.slice(1)}
                                value={application.category}
                                defaultValue={categories[0]}
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
                            <DefaultSelect
                                options={applicationObject.slice(1)}
                                value={application.applicationObjectTitle}
                                defaultValue={applicationObject[0]}
                                onChange={event => updateApplication({...application, applicationObjectTitle: event})}
                            />
                            <LimitedTextarea
                                value={application.description}
                                limit={600}
                                setValue={event => updateApplication({...application, description: event})}
                            />
                            <DefaultButton onClick={createApplication}>
                                Создать заявку
                            </DefaultButton>
                        </div>

                    </div>
                </div>
            </div>
        );
    }
;