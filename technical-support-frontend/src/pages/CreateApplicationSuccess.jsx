import React from 'react';
import {CreateApplicationResult} from "./CreateApplicationResult";
import {useParams} from "react-router-dom/cjs/react-router-dom";
import {getApplicationCategoryTitle} from "../utils/ApplicationUtils";

export const CreateApplicationSuccess = () => {
    const params = useParams()

    const success_text = `${getApplicationCategoryTitle(params.category).title} с номером ${params.id} создана успешно!`

    function get_links() {
        return (
            <div className="create-application-result__links">
                <a href="#">Перейти к заявке</a>
                <a href="#">К списку заявок</a>
            </div>
        )
    }

    return (
        <CreateApplicationResult
            img_name="success"
            text={success_text}
            links={get_links()}
        />
    );
};