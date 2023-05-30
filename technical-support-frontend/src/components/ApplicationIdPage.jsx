import React from 'react';

const ApplicationIdPage = ({application}) => {
    if (application) {
        return (
            <div>
                <div><b>Заявка #{application.basedApplicationDto.id}</b></div>
                <div>{application.category}</div>
            </div>
        );
    }
};

export default ApplicationIdPage;