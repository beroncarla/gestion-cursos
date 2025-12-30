-- =========================
-- DATOS DE PRUEBA
-- =========================

-- Usuarios
INSERT INTO usuario (nombre, email, password_hash, rol)
VALUES
('Admin', 'admin@demo.com', 'hash_admin', 'ADMIN'),
('Usuario Test', 'user@demo.com', 'hash_user', 'USER');

-- Cursos
INSERT INTO curso (nombre, cupo)
VALUES
('Introducción a Java', 30),
('Bases de Datos', 25);

-- Inscripciones
INSERT INTO inscripcion (id_usuario, id_curso, fecha_inscripcion, estado)
VALUES
(2, 1, '2024-03-01', 'INSCRIPTO'),
(2, 2, '2024-03-05', 'INSCRIPTO');
