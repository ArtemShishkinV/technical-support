import React, {useEffect, useState} from 'react';
import axios from "axios";

const Applications = () => {
    const [applications, updateApplications] = useState([])

    useEffect(() => {
        getActiveApplications()
    }, [])

    async function getActiveApplications() {
        const response = await axios.get("/api/application/active")
        console.log(response)
        updateApplications(response.data)
        console.log(applications)
    }

    return (
        <div>
            <button>Создать заявку</button>
            <hr/>
            <div>
                <button>Новые</button>
                <button>В работе</button>
                <button>Архив</button>
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