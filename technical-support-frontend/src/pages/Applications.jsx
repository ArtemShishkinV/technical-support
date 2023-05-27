import React, {useEffect, useMemo, useState} from 'react';
import {actives, archives, news} from "./data";
import "../css/Applications.css";
import {DefaultButton} from "../components/UI/DefaultButton";
import {DefaultFilter} from "../components/DefaultFilter";
import {DefaultCategories} from "../components/DefaultCategories";
import {ApplicationList} from "../components/ApplicationList";
import {sortApplications} from "../services/ApplicationSort";

const Applications = () => {
    const filterOptions = [
        {value: "", name: "Тип сортировки"},
        {value: 'default', name: 'По умолчанию'},
        {value: 'priority', name: 'Сначала важные'},
        {value: 'new', name: 'Сначала новые'},
        {value: 'old', name: 'Сначала старые'},
    ]

    const [applications, updateApplications] = useState([])
    const [filter, setFilter] = useState({sort: '', query: ''})

    const filteredApplications = useMemo(() => {
        if (applications.length !== 0) {
            return sortApplications(applications, filter.sort);
        }
        return applications
    }, [filter, applications])


    useEffect(() => {
        getActiveApplications()
    }, [])

    function getActiveApplications() {
        updateApplications(actives);
    }

    function getArchiveApplications() {
        updateApplications(archives)
    }

    function getNewApplications() {
        updateApplications(news)
    }

    return (
        <div className="applications-main">
            <div className="container">
                <div className="applications__inner">
                    <DefaultButton>Создать заявку</DefaultButton>
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
                    <ApplicationList applications={filteredApplications}/>
                </div>
            </div>
        </div>
    );
};

export default Applications;