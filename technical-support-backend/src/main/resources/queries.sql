-- Получение всех активных заявок
SELECT *
FROM application_software
         LEFT JOIN application a on a.id = application_software.application_id
         LEFT JOIN status s on a.status_id = s.id
WHERE s.title = 'В работе';

-- Получение всех новых заявок
SELECT *
FROM application_software
         LEFT JOIN application a on a.id = application_software.application_id
         LEFT JOIN status s on a.status_id = s.id
WHERE s.title = 'Создана';

-- Получение всех архивных заявок
SELECT *
FROM application_software
         LEFT JOIN application a on a.id = application_software.application_id
         LEFT JOIN status s on a.status_id = s.id
WHERE s.title = 'Отменена' or s.title = 'Решена';
