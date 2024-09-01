INSERT INTO user_details (id, name, birth_date)
VALUES (10001, 'Tom', current_date());
INSERT INTO user_details (id, name, birth_date)
VALUES (10002, 'Jon', current_date());
INSERT INTO user_details (id, name, birth_date)
VALUES (10003, 'Conor', current_date());

INSERT INTO post (id, description, user_id)
VALUES (20001, 'I try to learn Spring Boot.', 10001);
INSERT INTO post (id, description, user_id)
VALUES (30001, 'It was very fantastic destination.', 10002);
INSERT INTO post (id, description, user_id)
VALUES (40001, 'I am the best Lightweight ever', 10003);