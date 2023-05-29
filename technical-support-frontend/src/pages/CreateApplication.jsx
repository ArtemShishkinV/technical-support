import React, {useContext, useEffect, useMemo, useState} from 'react';
import "../css/CreateApplication.css";
import {DefaultSelect} from "../components/UI/DefaultSelect";
import {AppContext} from "../AppContext";
import CreateApplicationService from "../API/CreateApplicationService";
import {useFetching} from "../hooks/UseFetching";

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

        const [fetchModels, isModelsLoading, error] = useFetching(async () => {
            const response = await CreateApplicationService.getAllModels();
            setPriorities([...priorities, response.priorities])
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
            console.log(types)
        }, [application.category])


        function showSelectType() {
            if (application.category === "device")
                return getDeviceApplicationTypes()
            if (application.category === "software")
                return getSoftwareApplicationTypes()
        }

        function getDeviceApplicationTypes() {
            // /api/application-device-types
            return [
                {value: "device1", name: "Выдача"},
                {value: "device2", name: "Ремонт"}
            ]
        }

        function getSoftwareApplicationTypes() {
            // /api/application-device-types
            return [
                {value: "", name: "Выберите тип"},
                {value: "software1", name: "Проблема с лицензией"},
                {value: "software2", name: "Трудности в работе"}
            ]
        }

        // function showTypes(types) {
        //     console.log(types)
        //     if (types)
        //         return (
        //             <DefaultSelect
        //                 options={types.slice(1)}
        //                 value={application.type}
        //                 defaultValue={types[0]}
        //                 onChange={event => updateApplication({...application, type: event})}
        //             />
        //         )
        // }

        // function showPriorities() {
        //     if (!isLoading)
        //         return (
        //             <DefaultSelect
        //                 options={priorities.slice(1)}
        //                 value={application.priority}
        //                 defaultValue={priorities[0]}
        //                 onChange={event => updateApplication({...application, priority: event})}
        //             />
        //         )
        //     console.log(priorities)
        // }

        return (
            <div className="create-application">
                <div className="container">
                    <div className="create-application__inner">
                        <h1>Создание заявки</h1>
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
                        {/*{showTypes(types)}*/}
                        {/*{showPriorities()}*/}
                    </div>
                </div>
            </div>
        );
    }
;