import { Button, Modal } from 'antd';
import React, {useState} from 'react';
import "../css/Modal.css";

export const DefaultModal = ({parModalText, buttonText, title, callback, ...args}) => {
    const [open, setOpen] = useState(false);
    const [confirmLoading, setConfirmLoading] = useState(false);
    const [modalText, setModalText] = useState(parModalText);
    const showModal = () => {
        setOpen(true);
    };
    const handleOk = async () => {
        setModalText('Подтверждение операции...');
        setConfirmLoading(true);
        await callback(args.application, args.newStatus);
        setTimeout(() => {
            setOpen(false);
            setConfirmLoading(false);
        }, 2000);
    };
    const handleCancel = () => {
        setOpen(false);
    };

    return (
        <div className="myModal">
            <Button type="primary" onClick={showModal}>
                {buttonText}
            </Button>
            <Modal
                title={title}
                open={open}
                onOk={handleOk}
                confirmLoading={confirmLoading}
                onCancel={handleCancel}
            >
                <p>{modalText}</p>
            </Modal>
        </div>
    );
};