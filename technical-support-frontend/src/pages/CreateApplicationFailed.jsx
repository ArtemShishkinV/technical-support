import React from 'react';
import {CreateApplicationResult} from "./CreateApplicationResult";
import {useParams} from "react-router-dom/cjs/react-router-dom";
import {getApplicationCategoryTitle} from "../utils/ApplicationUtils";

export const CreateApplicationFailed = () => {
    const params = useParams()

    const success_text = `Создание заявки закончилось с ошибкой, попробуйте повторить попытку!`

    const applicationsLink = "/applications";
    const createApplicationLink = "/create-application";

    function get_links() {
        return (
            <div className="create-application-result__links">
                <a href={createApplicationLink}>Создать заявку</a>
                <a href={applicationsLink}>К списку заявок</a>
            </div>
        )
    }

    return (
        <CreateApplicationResult
            img_name="fail"
            text={success_text}
            links={get_links()}
        />
    );
};