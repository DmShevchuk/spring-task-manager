ALTER TABLE IF EXISTS users
    ADD role VARCHAR DEFAULT ('ROLE_USER');

INSERT INTO users (user_id, name, middle_name, last_name, email, password, role)
VALUES (1, 'Admin', 'Admin', 'Admin', 'admin@mail.ru',
        '$2a$10$azVqTJ/iWvxRFoAT6IxlceUIJyiIHeclZ7A2u20ljmlD914Mjl6u6', 'ROLE_ADMIN'),

       (2, 'Ivan', 'Sergeevich', 'Petrov', 'petrov@mail.ru',
        '$2a$10$azVqTJ/iWvxRFoAT6IxlceUIJyiIHeclZ7A2u20ljmlD914Mjl6u6', 'ROLE_USER'),

       (3, 'Vladimir', 'Vladimirovich', 'Smirnov', 'smirnov@mail.ru',
        '$2a$10$azVqTJ/iWvxRFoAT6IxlceUIJyiIHeclZ7A2u20ljmlD914Mjl6u6', 'ROLE_USER');


INSERT INTO projects (project_id, project_title, project_description)
VALUES (1, 'Project 1', 'Description 1');

INSERT INTO users_projects (id, id_of_user, id_of_project)
VALUES (1, 2, 1),
       (2, 3, 1);

INSERT INTO tasks (task_id,
                   task_title,
                   task_description,
                   deadline,
                   task_type,
                   id_of_user,
                   id_of_project)
VALUES (1, 'Title', 'Description', '2022-08-15', 'DONE', 2, 1),
       (2, 'Title-2', 'Description', '2022-08-15', 'IN_PROGRESS', 3, 1)

