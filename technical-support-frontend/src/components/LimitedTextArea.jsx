import React from 'react';
import "../css/LimitedTextArea.css";

export const LimitedTextarea = ({rows, cols, value, limit, setValue}) => {
    const [content, setContent] = React.useState([value]);

    const setFormattedContent = React.useCallback(
        text => {
            setContent(text.slice(0, limit));
        },
        [limit, setContent]
    );

    return (
        <div className="myTextarea">
            <p>
                Кол-во символов: {content.length}/{limit}
            </p>
            <textarea
                rows={rows}
                cols={cols}
                onChange={event => {
                    setValue(event.target.value)
                    setFormattedContent(event.target.value)
                }
                }
                value={content}
            />
        </div>
    );
};