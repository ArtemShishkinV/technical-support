import React from 'react';
import {ApplicationListItem} from "./ApplicationListItem";

export const ApplicationList = ({applications, isActive}) => {
    if (applications.length)
        return (
            <div className="application-list">
                {applications.map((application) =>
                    <ApplicationListItem application={application} key={application.basedApplicationDto.id}/>
                )}
            </div>
        )
    return (
        <div className="application-list">
            Заявки не найдены!
        </div>
    )
};