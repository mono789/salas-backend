-- Inserting roles
INSERT INTO role (name) VALUES (0);
INSERT INTO role (name) VALUES (1);
INSERT INTO role (name) VALUES (2);
INSERT INTO role (name) VALUES (3);

-- Inserting customers
INSERT INTO customer (customerId, firstname, lastName, email, roleId, password)
VALUES ('f4789fa7-e512-4c56-b19a-44eafc79fc70', 'Juan', 'Doe', 'juan.doe@example.com', 1, 'co$2a$10$osvKabUtRtdOecK6n77pc.iUxwqWUTgwJPTjtJOtR4gQ5Ug.RVWo6');
INSERT INTO customer (customerId, firstname, lastName, email, roleId, password)
VALUES ('131a8261-c37e-4970-bb2d-e527a703b80c', 'Ana', 'Smith', 'ana.smith@example.com', 2, '$2a$10$.41fNWM9fGg1SutUQPZVfeYA8TZXxIAifGiHIRLA0aPvmoZ/B.lrO');
INSERT INTO customer (customerId, firstname, lastName, email, roleId, password)
VALUES ('3ceb9085-6a4a-4aab-b4b4-d61ee4bd1b16', 'Roberto', 'Johnson', 'roberto.johnson@example.com', 3, '$2a$10$SYICIuZ89GsQOvnwEyeDGerlu9Dvm/w2kmIv/PWFA.gWx2VdA14uW');

-- Inserting rooms
INSERT INTO room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (211012, 20, '21', '101', 'Sala de Conferencias 1', 2);
INSERT INTO room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (202010, 30, '20', '201', 'Sala de Reuniones 1', 0);
INSERT INTO room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (182181, 15, '18', '218', 'LIS - Sala 1', 1);

INSERT INTO room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (193101, 25, '19', '310', 'Sala de Capacitación 1', 0);

INSERT INTO room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (221102, 10, '22', '110', 'Sala de Talleres', 1);

INSERT INTO room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (151210, 40, '15', '1210', 'Auditorio Principal', 0);

-- Inserting applications
INSERT INTO application (applicationName, version) VALUES ('Microsoft Office', '2021');
INSERT INTO application (applicationName, version) VALUES ('Zoom', '5.0');
INSERT INTO application (applicationName, version) VALUES ('Adobe Photoshop', '2022');
INSERT INTO application (applicationName, version) VALUES ('Visual Studio', '2019');
INSERT INTO application (applicationName, version) VALUES ('Netbeans', '2013');
INSERT INTO application (applicationName, version) VALUES ('InteliJ', '2025');
INSERT INTO application (applicationName, version) VALUES ('Spotify', '2029');


-- Assigning applications to rooms
INSERT INTO roomsoftware (applicationId, roomId) VALUES (1, 211012);
INSERT INTO roomsoftware (applicationId, roomId) VALUES (2, 202010);
INSERT INTO roomsoftware (applicationId, roomId) VALUES (3, 182181);
INSERT INTO roomsoftware (applicationId, roomId) VALUES (4, 193101);  -- Microsoft Office en Sala de Capacitación 1
INSERT INTO roomsoftware (applicationId, roomId) VALUES (5, 221102);  -- Zoom en Sala de Talleres
INSERT INTO roomsoftware (applicationId, roomId) VALUES (6, 151210);  -- Adobe Photoshop en Auditorio Principal

-- Inserting restrictions
INSERT INTO restriction (description) VALUES ('No se permiten alimentos o bebidas');
INSERT INTO restriction (description) VALUES ('No fumar en la sala');
INSERT INTO restriction (description) VALUES ('No se permiten mascotas');

-- Assigning restrictions to rooms
INSERT INTO roomrestriction (restrictionId, roomId) VALUES (1, 211012);
INSERT INTO roomrestriction (restrictionId, roomId) VALUES (2, 202010);
INSERT INTO roomrestriction (restrictionId, roomId) VALUES (3, 182181);
-- Asignar restricciones a las nuevas salas
INSERT INTO roomrestriction (restrictionId, roomId) VALUES (1, 193101);  -- No se permiten alimentos o bebidas
INSERT INTO roomrestriction (restrictionId, roomId) VALUES (2, 221102);  -- No fumar en la sala
INSERT INTO roomrestriction (restrictionId, roomId) VALUES (3, 151210);  -- No se permiten mascotas


-- Inserting implements
INSERT INTO implement (implementName, state) VALUES ('Proyector', 0);
INSERT INTO implement (implementName, state) VALUES ('Pizarra', 1);
INSERT INTO implement (implementName, state) VALUES ('Sistema de Sonido', 2);
INSERT INTO implement (implementName, state) VALUES ('Escritorios', 1);
INSERT INTO implement (implementName, state) VALUES ('Oculus', 1);
INSERT INTO implement (implementName, state) VALUES ('Impresora', 1);


-- Assigning implements to rooms
INSERT INTO roomimplement (implementId, roomId) VALUES (1, 211012);
INSERT INTO roomimplement (implementId, roomId) VALUES (2, 202010);
INSERT INTO roomimplement (implementId, roomId) VALUES (2, 182181);
-- Asignar implementos a las nuevas salas
INSERT INTO roomimplement (implementId, roomId) VALUES (4, 193101);  -- Proyector en Sala de Capacitación 1
INSERT INTO roomimplement (implementId, roomId) VALUES (5, 221102);  -- Pizarra en Sala de Talleres
INSERT INTO roomimplement (implementId, roomId) VALUES (6, 151210);  -- Sistema de Sonido en Auditorio Principal


-- Inserting reservation states
INSERT INTO reservationstate (description) VALUES (0);
INSERT INTO reservationstate (description) VALUES (1);
INSERT INTO reservationstate (description) VALUES (2);

-- Inserting reservations with date and time, separated by exactly two hours
INSERT INTO reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Reunión de la Junta', 'Reunión mensual de la junta', '2023-10-25 14:30:00', '2023-10-25 16:30:00', 0, 1, 211012, 'f4789fa7-e512-4c56-b19a-44eafc79fc70');
INSERT INTO reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Sesión de Capacitación', 'Capacitación para nuevos empleados', '2023-10-28 09:00:00', '2023-10-28 11:00:00', 1, 2, 182181, '131a8261-c37e-4970-bb2d-e527a703b80c');
INSERT INTO reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Reunión de Equipo', 'Reunión semanal del equipo', '2023-10-27 15:00:00', '2023-10-27 17:00:00', 1, 3, 202010, '3ceb9085-6a4a-4aab-b4b4-d61ee4bd1b16');

