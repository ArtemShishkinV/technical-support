import React, {useEffect, useMemo, useState} from 'react';
import "../css/Applications.css";
import {DefaultButton} from "../components/UI/DefaultButton";
import {DefaultFilter} from "../components/DefaultFilter";
import {DefaultCategories} from "../components/DefaultCategories";
import {ApplicationList} from "../components/ApplicationList";
import {searchApplications, sortApplications} from "../services/ApplicationSort";
import ApplicationService from "../API/ApplicationService";
import {DefaultLoader} from "../components/UI/DefaultLoader";
import {AuthService} from "../API/AuthService";

const Applications = () => {
    const user = AuthService.isAuthenticated().user;
    const filterOptions = [
        {id: "", title: "Тип сортировки"},
        {id: 'default', title: 'По умолчанию'},
        {id: 'priority', title: 'Сначала важные'},
        {id: 'new', title: 'Сначала новые'},
        {id: 'old', title: 'Сначала старые'},
    ]

    const [applications, updateApplications] = useState([])

    const [filter, setFilter] = useState({sort: '', query: ''})
    const [isLoading, setLoading] = useState(false)

    const filteredApplications = useMemo(() => {
        if (applications.length !== 0) {
            console.log(filter.sort)
            let newApplications = sortApplications(applications, filter.sort);
            newApplications = searchApplications(applications, filter.query);
            return newApplications;
        }
        return applications
    }, [filter, applications])


    useEffect(() => {
        getNewApplications()
    }, [])

    async function getApplications(callback) {
        setLoading(true)
        setTimeout(async () => {
            const resp = await callback();
            updateApplications(resp);
            console.log(resp)
            setLoading(false)
        }, 1000)
    }

    async function getActiveApplications() {
        await getApplications(ApplicationService.getActiveApplications)
    }

    async function getArchiveApplications() {
        await getApplications(ApplicationService.getArchiveApplications)
    }

    async function getNewApplications() {
        await getApplications(ApplicationService.getNewApplications)
    }

    return (
        <div className="applications-main">
            <div className="container">
                <div className="applications__inner">
                    {user.role === "Работник"
                        ? <a href="/create-application">
                            <DefaultButton>Создать заявку</DefaultButton>
                        </a> : <div></div>
                    }

                    <hr/>
                    <DefaultCategories
                        categories={[
                            {callback: getNewApplications, name: 'Новые'},
                            {callback: getActiveApplications, name: 'В работе'},
                            {callback: getArchiveApplications, name: 'Архив'}
                        ]}
                    />
                    <DefaultFilter
                        filter={filter}
                        setFilter={setFilter}
                        options={filterOptions}
                    />
                    {
                        isLoading
                            ? <DefaultLoader/>
                            : <ApplicationList applications={filteredApplications}/>
                    }

                </div>
            </div>
        </div>
    );
};

export default Applications;