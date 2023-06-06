import React from 'react';
import {DefaultInput} from "./UI/DefaultInput";
import {message, Upload} from "antd";

export const DefaultComment = () => {
    const {Dragger} = Upload
    const props = {
        name: 'file',
        multiple: true,
        action: 'https://www.mocky.io/v2/5cc8019d300000980a055e76',
        onChange(info) {
            const { status } = info.file;
            if (status !== 'uploading') {
                console.log(info.file, info.fileList);
            }
            if (status === 'done') {
                message.success(`${info.file.name} file uploaded successfully.`);
            } else if (status === 'error') {
                message.error(`${info.file.name} file upload failed.`);
            }
        },
        onDrop(e) {
            console.log('Dropped files', e.dataTransfer.files);
        },
    };

    return (
        <div>
            <DefaultInput
                placeholder={"Введите текст сообщения..."}
            />
            <Dragger {...props}>
                <p className="ant-upload-hint">
                    Перетащите файл, чтобы сделать вложение
                </p>
            </Dragger>
        </div>
    );
};

export default DefaultComment;