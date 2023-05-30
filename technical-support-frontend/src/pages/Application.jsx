import React, {useEffect, useState} from 'react';
import {useFetching} from "../hooks/UseFetching";
import ApplicationService from "../API/ApplicationService";
import {useParams} from "react-router-dom/cjs/react-router-dom";
import {DefaultLoader} from "../components/UI/DefaultLoader";
import "../css/style.css";
import ApplicationIdPage from "../components/ApplicationIdPage";
import "../css/Application.css";

export const Application = () => {
    const params = useParams();

    const [application, setApplication] = useState()

    const [fetchApplication, isLoading, error] = useFetching(async () => {
        const resp = await ApplicationService.getApplication(`/${params.category}/${params.id}`)
        setApplication(resp.data)
    })

    useEffect(() => {
        fetchApplication()
    }, [])

    return (
        <div className="application__wrapper">
            <div className="container">
                <div className="application__inner">
                    {isLoading
                        ? <DefaultLoader/>
                        : <ApplicationIdPage application={application}/>
                    }
                </div>
            </div>
        </div>
    );
};