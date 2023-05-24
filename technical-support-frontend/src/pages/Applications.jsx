import React, {useEffect, useState} from 'react';
import {actives, archives, news} from "./data";
import "../css/Applications.css";
import {DefaultButton} from "../components/UI/DefaultButton";
import {DefaultFilter} from "../components/DefaultFilter";

const Applications = () => {
    const filterOptions = [
        {value: 'default', name: 'По умолчанию'},
        {value: 'priority', name: 'Сначала важные'},
        {value: 'new', name: 'Сначала новые'},
        {value: 'old', name: 'Сначала старые'},
    ]

    const [applications, updateApplications] = useState([])
    const [filter, setFilter] = useState({sort: '', query: ''})


    useEffect(() => {
        getActiveApplications()
    }, [])


    function getActiveApplications() {
        updateApplications(actives)
    }

    function getArchiveApplications() {
        updateApplications(archives)
    }

    function getNewApplications() {
        updateApplications(news)
    }


    // async function getArchiveApplications() {
    //     const response = await axios.get("/api/application/archive")
    //     updateApplications(response.data)
    // }

    // async function getNewApplications() {
    //     const response = await axios.get("/api/application/new")
    //     updateApplications(response.data)
    // }

    // async function getActiveApplications() {
    //     const response = await axios.get("/api/application/active")
    //     updateApplications(response.data)
    // }

    return (
        <div className="applications-main">
            <div className="container">
                <div className="applications-inner">
                    <DefaultButton>Создать заявку</DefaultButton>
                    <hr/>
                    <div>
                        <button onClick={getNewApplications}>Новые</button>
                        <button onClick={getActiveApplications}>В работе</button>
                        <button onClick={getArchiveApplications}>Архив</button>
                    </div>
                    <DefaultFilter
                        filter={filter}
                        setFilter={setFilter}
                        options={filterOptions}
                    />
                    <div className="application-list">
                        {applications.map((application) =>
                            <div className="application">
                                <div className="application-id">Заявка #{application.basedApplicationDto.id}</div>
                                <div className="application-category">{application.category}</div>
                                <div className="application-object">
                                    {application.applicationObjectDto.category}: {application.applicationObjectDto.title}
                                </div>
                                <div className="application-type">{application.type}</div>
                                <div className="application-employee">
                                    {application.basedApplicationDto.executor.lastName} {application.basedApplicationDto.executor.firstName} {application.basedApplicationDto.executor.middleName}
                                </div>
                                <div className="application-status">{application.basedApplicationDto.status}</div>
                                <div className="application-date">{application.basedApplicationDto.createdAt}</div>
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Applications;