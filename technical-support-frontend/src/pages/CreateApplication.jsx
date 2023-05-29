import React, {useContext, useEffect, useMemo, useState} from 'react';
import "../css/CreateApplication.css";
import {DefaultSelect} from "../components/UI/DefaultSelect";
import {AppContext} from "../AppContext";
import CreateApplicationService from "../API/CreateApplicationService";
import {useFetching} from "../hooks/UseFetching";
import {LimitedTextarea} from "../components/LimitedTextArea";

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
                applicationObjectDto: "",
                type: "",
                description: "",
                initiator: user,
                executor: null,
                priority: "",
            }
        ])

        const [model, setModel] = useState([])

        const [priorities, setPriorities] = useState([
            {id: 0, title: "Выберите приоритет"}
        ])

        const [types, setTypes] = useState([
            {id: 0, title: "Выберите тип"}
        ])

        const x = () => {

        }


        const [fetchModels, isModelsLoading, error] = useFetching(async () => {
            const response = await CreateApplicationService.getAllModels();
            setPriorities([priorities[0], ...response.priorities])
            setModel(response)
        })

        useEffect(() => {
            fetchModels()
        }, [])

        useMemo(() => {
            const temp = [types[0]]
            if (application.category === "Заявка на технику")
                setTypes([...temp, ...model.applicationDeviceTypes])
            if (application.category === "Заявка на ПО")
                setTypes([...temp, ...model.applicationSoftwareTypes])
        }, [application.category])

        // useMemo(() => {
        //
        // }, [application.])

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
                                options={priorities.slice(1)}
                                value={application.priority}
                                defaultValue={priorities[0]}
                                onChange={event => updateApplication({...application, priority: event})}
                            />
                            <DefaultSelect
                                options={priorities.slice(1)}
                                value={application.priority}
                                defaultValue={priorities[0]}
                                onChange={event => updateApplication({...application, priority: event})}
                            />
                            <LimitedTextarea
                                value={application.description}
                                limit={600}
                            />
                            <DefaultButton

                            />

                            {/*</DefaultButton>*/}
                        </div>

                    </div>
                </div>
            </div>
        );
    }
;