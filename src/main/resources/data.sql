INSERT INTO positions (id, name) VALUES 
(1, 'Asesor de ventas'),
(2, 'Administrador'),
(3, 'Soporte')
ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, name, age, date_of_admission, position_id) VALUES 
(4, 'John Wick', 38, CURRENT_DATE, 3),
(5, 'Sylvester Stallone', 70, CURRENT_DATE, 1),
(6, 'Jean Claude Van Damme', 64, CURRENT_DATE, 2),
(7, 'Harry Potter', 18, CURRENT_DATE, 3),
(8, 'Jackie Chan', 67, CURRENT_DATE, 2)
ON CONFLICT (id) DO NOTHING;

SELECT setval('hibernate_sequence', 9, false);