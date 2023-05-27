import React from 'react';
import {ApplicationListItem} from "./ApplicationListItem";

export const ApplicationList = ({applications, isActive}) => {
    return (
        <div className="application-list">
            {applications.map((application) =>
                <ApplicationListItem application={application} key={application.basedApplicationDto.id}/>
            )}
        </div>
    )
};