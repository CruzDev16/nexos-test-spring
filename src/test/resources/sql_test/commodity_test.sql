INSERT INTO positions (id, name) VALUES 
(1, 'Asesor de ventas');

INSERT INTO users (id, name, age, date_of_admission, position_id) VALUES 
(1, 'John Wick', 38, CURRENT_DATE, 1),
(2, 'Harry Potter', 38, CURRENT_DATE, 1);

INSERT INTO commodity (id, name, product, quantity, date_of_admission, creator_user_id) VALUES 
(1, 'Carros', 'Chevrolet', 10, CURRENT_DATE, 1),
(2, 'Barcos', 'Yate', 5, CURRENT_DATE, 2),
(3, 'Ropa', 'Sueter', 35, CURRENT_DATE, 1);