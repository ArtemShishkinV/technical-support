import React, {useEffect, useState} from 'react';
import {actives, archives, news} from "./data";
import cl from './Applications.module.css';
import {DefaultNavbar} from "../components/DefaultNavbar";

const Applications = () => {
    const [applications, updateApplications] = useState([])

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
        <div className={cl.main}>
            <button>Создать заявку</button>
            <hr/>
            <div>
                <button onClick={getNewApplications}>Новые</button>
                <button onClick={getActiveApplications}>В работе</button>
                <button onClick={getArchiveApplications}>Архив</button>
            </div>
            <div>
                <input type="text"/>
                <select>
                    <option value="" selected>По умолчанию</option>
                    <option value="">Сначала важные</option>
                    <option value="">Сначала новые</option>
                    <option value="">Сначала старые</option>
                </select>
            </div>
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
    );
};

export default Applications;