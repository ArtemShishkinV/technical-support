import React, {useContext, useEffect, useMemo, useState} from 'react';
import "../css/CreateApplication.css";
import {DefaultSelect} from "../components/UI/DefaultSelect";
import {AppContext} from "../AppContext";

export const CreateApplication = () => {
        const categories = [
            {value: "", name: "Выберите категорию"},
            {value: "device", name: "Заявка на технику"},
            {value: "software", name: "Заявка на ПО"}
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

        const [priorities, setPriorities] = useState([])

        useEffect(() => {
            //api/priorities
            setPriorities([
                {value: "", name: "Выберите приоритет"},
                {value: "Критический", name: "Критический"},
                {value: "Высокий", name: "Высокий"},
                {value: "Низкий", name: "Низкий"},
            ])
        }, [])


        const types = useMemo(() => {
            return showSelectType()
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
                {value: "", name: "Выберите тип"},
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

        function showTypes(types) {
            console.log(types)
            if (types)
                return (
                    <DefaultSelect
                        options={types.slice(1)}
                        value={application.type}
                        defaultValue={types[0]}
                        onChange={event => updateApplication({...application, type: event})}
                    />
                )
        }

        function showPriorities() {
            if (application.type)
                return (
                    <DefaultSelect
                        options={priorities.slice(1)}
                        value={application.priority}
                        defaultValue={priorities[0]}
                        onChange={event => updateApplication({...application, priority: event})}
                    />
                )
        }

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
                        {showTypes(types)}
                        {showPriorities()}
                    </div>
                </div>
            </div>
        );
    }
;