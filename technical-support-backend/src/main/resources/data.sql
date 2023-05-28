INSERT INTO office(build_number, city, street)
VALUES (14, 'Рязань', 'Семашко'),
       (25, 'Москва', 'Лермонтова'),
       (5, 'Москва', 'Кутузова'),
       (1, 'Воронеж', 'Ленина');

INSERT INTO workplace(floor, room_number, table_number, office_id)
VALUES (2, 'К1', 1, 1),
       (2, 'К1', 2, 1),
       (2, 'К1', 3, 1),
       (4, 'К5', 1, 1),
       (1, 'К1', 1, 2),
       (1, 'К2', 1, 2),
       (1, 'К3', 1, 2),
       (1, 'К1', 1, 3),
       (1, 'К1', 1, 3),
       (3, 'К1', 1, 4),
       (3, 'К2', 1, 4),
       (3, 'К2', 2, 4),
       (4, 'К6', 1, 1);

INSERT INTO job_post(title)
VALUES ('Инженер'),
       ('Старший инженер'),
       ('Ведущий инженер'),
       ('Главный инженер');

INSERT INTO department(title)
VALUES ('Отдел тестирования'),
       ('Отдел интеграции'),
       ('Отдел разработки'),
       ('Отдел сопровождения'),
       ('Отдел обслуживания'),
       ('Отдел аналитики');

INSERT INTO device_condition(title)
VALUES ('Исправна'),
       ('Требует ремонта'),
       ('Списана');

INSERT INTO device_type(title, count_replacement_days)
VALUES ('Монитор', 1000),
       ('Комплект б.п. клавиатура и мышь', 365),
       ('Док-станция', 1000),
       ('Гарнитура', 365),
       ('Ноутбук', 2000);

INSERT INTO employee(staff_number, birth_day, email, first_name, middle_name, last_name, login, password, phone_number,
                     is_online, role, department_id, job_post_id, workplace_id)
VALUES (nextval('employee_seq'), '2002-01-31', 'admin@mail.ru', 'Артем', 'Игоревич', 'Шишкин', 'user',
        '$2a$12$Z3qq3W15pwNxB5VgvhD28u7DKwxP6CJFmzNc802xf2hps9lmbis9m', '89113235670', false, 'EMPLOYEE', 3, 2, 1),
       (nextval('employee_seq'), '2000-05-23', 'qwe@mail.ru', 'Иванов', 'Иван', 'Иванович', 'qwe',
        '$2a$12$yvw49zR3BrDO0qQ7jX5U4ujQ5l15kYuAIqFXOODAD.rEc54H4NaOy', '89153115283', false, 'EMPLOYEE', 1, 2, 2),
       (nextval('employee_seq'), '1995-02-02', 'test@mail.ru', 'Петров', 'Петр', 'Петрович', 'test',
        '$2a$12$Rx7dprHETaLJHK1559eII.HCFGrWlrP5PWhUsyUOdIZ5VuSM1qXzq', '89200005283', false, 'SUPPORT', 4, 1, 4),
       (nextval('employee_seq'), '1993-04-07', 'test@mail.ru', 'Алексеев', 'Алексей', 'Алексеевич', 'admin',
        '$2a$12$aUJqAWKDY1oTolyEKL0aLejbQptzz8THFT', '89231112244', false, 'ADMIN', 3, 3, 3),
       (nextval('employee_seq'), '1995-02-02', 'test@mail.ru', 'Самонов', 'Самон', 'Петрович', 'xre',
        '$2a$12$Rx7dprHETaLJHK1559eII.HCFGrWlrP5PWhUsyUOdIZ5VuSM1qXzq', '89333335283', false, 'SUPPORT', 4, 1, 13);

INSERT INTO device(serial_number, device_type_id, device_condition_id, owner_staff_number, title, description)
VALUES (nextval('device_seq'), 1, 1, 10000000, 'Huawei Mateview',
        'Оборудован безрамочным экраном высокого разрешения, который позволяет сосредоточиться на деталях изображения при работе'),
       (nextval('device_seq'), 1, 1, 10000001, 'Xiaomi Redmi Monitor',
        'Обеспечивает реалистичное детализированное изображение для удобной работы'),
       (nextval('device_seq'), 2, 1, null, 'Logitech MK270',
        'Для подключения комплекта используется беспроводной интерфейс, устройства работают от стандартного нано-приемника.'),
       (nextval('device_seq'), 2, 3, null, 'Logitech MK270',
        'Для подключения комплекта используется беспроводной интерфейс, устройства работают от стандартного нано-приемника'),
       (nextval('device_seq'), 2, 1, 10000000, 'Logitech MK235',
        'Комплект из двух устройств, ориентированных для использования с офисными и домашними ПК'),
       (nextval('device_seq'), 2, 1, 10000001, 'Logitech MK235',
        'Комплект из двух устройств, ориентированных для использования с офисными и домашними ПК'),
       (nextval('device_seq'), 3, 1, 10000000, 'KEYRON UCN3442', 'Включает в себя огромный набор полезных разъемов'),
       (nextval('device_seq'), 3, 1, 10000001, 'KEYRON UCN3442', 'Включает в себя огромный набор полезных разъемов'),
       (nextval('device_seq'), 3, 1, null, 'KEYRON UCN3442', 'Включает в себя огромный набор полезных разъемов'),
       (nextval('device_seq'), 4, 2, 10000000, 'Edifier CC200',
        'C одним наушником отличается удобством в повседневной эксплуатации'),
       (nextval('device_seq'), 4, 1, 10000001, 'EPOS I Sennheiser IMPACT MB Pro 1',
        'Моно Bluetooth бизнес-гарнитура премиального класса для профессионалов, которым требуются свобода беспроводной связи, великолепное качество звука, и исключительный комфорт при использовании'),
       (nextval('device_seq'), 5, 1, 10000000, 'Apple MacBook Air (M1, 2020)',
        'Самый тонкий и легкий ноутбук Apple теперь стал суперсильным благодаря чипу Apple M1. Он быстро справляется с вашими задачами, задействуя потрясающую скорость 8-ядерного процессора.'),
       (nextval('device_seq'), 5, 2, 10000001, 'Apple MacBook Air (M1, 2020)',
        'Самый тонкий и легкий ноутбук Apple теперь стал суперсильным благодаря чипу Apple M1. Он быстро справляется с вашими задачами, задействуя потрясающую скорость 8-ядерного процессора.');


INSERT INTO application_device_type(title)
VALUES ('Выдача'),
       ('Ремонт'),
       ('Сдача');

INSERT INTO application_software_type(title)
VALUES ('Проблема с установкой'),
       ('Проблема в работе'),
       ('Проблема с лицензией');

INSERT INTO software_type(title)
VALUES ('Системное ПО'),
       ('Прикладное ПО'),
       ('Средства для разработки');

INSERT INTO software(title, description, software_type_id)
VALUES ('Excel', 'программа для работы с электронными таблицами, созданная корпорацией Microsoft для Microsoft Windows',
        2),
       ('Word',
        'текстовый процессор, предназначенный для создания, просмотра, редактирования и форматирования текстов статей, деловых бумаг, а также иных документов, с локальным применением простейших форм таблично-матричных алгоритмов',
        2),
       ('Notepad++',
        'свободный текстовый редактор с открытым исходным кодом для Windows с подсветкой синтаксиса, разметки, а также языков описания аппаратуры VHDL и Verilog',
        2),
       ('GIMP', 'свободно распространяемый растровый графический редактор', 2),
       ('FAR Manager', 'консольный файловый менеджер для операционных систем семейства Microsoft Windows и Linux', 1),
       ('Realtek High Definition Audio Driver',
        'одним из самых популярных аудио-драйверов, позволяющий тонко настраивать уровень и качество звука в Windows',
        1),
       ('IntelliJ IDEA',
        'интегрированная среда разработки программного обеспечения для многих языков программирования, в частности Java, JavaScript, Python, разработанная компанией JetBrains.',
        3),
       ('Visual Studio Code', 'редактор исходного кода, разработанный Microsoft для Windows, Linux и macOS.', 3);


INSERT INTO priority(title, auto_appointment_hours, points)
VALUES ('Низкий', 24, 1),
       ('Высокий', 4, 5),
       ('Критический', 1, 100);

INSERT INTO status(title)
VALUES ('Создана'),
       ('В работе'),
       ('Решена'),
       ('Отменена'),
       ('Просрочена специалистом');

INSERT INTO application(created_at, description, appointment_at, is_offline, solved_at, executor_staff_number,
                        initiator_staff_number, priority_id, status_id)
VALUES ('2023-05-16 15:36:38', 'Не открывается программа', '2023-05-30 15:36:38', false, null, 10000003, 10000000, 1,
        1),
       ('2023-05-16 16:22:17', 'Автоматическое закрытие программы спустя пару часов работы', '2023-05-16 16:22:17',
        false, null, 10000003, 10000001, 1, 1),
       ('2023-05-17 12:23:17', 'Не работает микрофон', '2023-05-31 12:23:17', false, null, 10000003, 10000000, 2, 1),
       ('2023-05-17 15:00:17', 'При входе в ПК выдает ошибку, что не удалось подключиться к серверу',
        '2023-05-31 15:00:17', true, null, 10000003, 10000001, 3, 1);

INSERT INTO application_software(application_id, application_software_type_id, software_id)
VALUES (1, 2, 1),
       (2, 2, 7);

INSERT INTO application_device(application_id, application_device_type_id, device_serial_number)
VALUES (3, 2, 100009),
       (4, 2, 100012);
