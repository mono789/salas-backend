-- Inserting roles
INSERT INTO Role (roleName) VALUES ('ADMIN');
INSERT INTO Role (roleName) VALUES ('PROFESSOR');
INSERT INTO Role (roleName) VALUES ('MONITOR');
INSERT INTO Role (roleName) VALUES ('STUDENT');


-- Inserting customers
INSERT INTO Customer (customerId, firstname, lastName, username, roleId, password)
VALUES (123456, 'John', 'Doe', 'john.doe@example.com', 1,'password1');
INSERT INTO Customer (customerId, firstname, lastName, username, roleId, password)
VALUES (789012, 'Jane', 'Smith', 'jane.smith@example.com', 2,'password1');
INSERT INTO Customer (customerId, firstname, lastName, username, roleId, password)
VALUES (345678, 'Bob', 'Johnson', 'bob.johnson@example.com', 3,'oo');

-- Inserting rooms
INSERT INTO Room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (211012, 20, 21, 101, 'Conference Room 1', 2);
INSERT INTO Room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (202010, 30, 20, 201, 'Meeting Room 1', 0);
INSERT INTO Room (roomId, computerAmount, building, roomNum, roomName, subRoom)
VALUES (182181, 15, 18, 218, 'LIS - Sala 1', 1);

-- Inserting applications
INSERT INTO Application (applicationName, version) VALUES ('Microsoft Office', '2021');
INSERT INTO Application (applicationName, version) VALUES ('Zoom', '5.0');
INSERT INTO Application (applicationName, version) VALUES ('Adobe Photoshop', '2022');

-- Assigning applications to rooms
INSERT INTO RoomSoftware (applicationId, roomId) VALUES (1, 211012);
INSERT INTO RoomSoftware (applicationId, roomId) VALUES (2, 202010);
INSERT INTO RoomSoftware (applicationId, roomId) VALUES (3, 182181);

-- Inserting restrictions
INSERT INTO Restriction (description) VALUES ('No food or drinks allowed');
INSERT INTO Restriction (description) VALUES ('No smoking in the room');
INSERT INTO Restriction (description) VALUES ('No pets allowed');

-- Assigning restrictions to rooms
INSERT INTO RoomRestriction (restrictionId, roomId) VALUES (1, 211012);
INSERT INTO RoomRestriction (restrictionId, roomId) VALUES (2, 202010);
INSERT INTO RoomRestriction (restrictionId, roomId) VALUES (3, 182181);

-- Inserting implements
INSERT INTO Implement (implementName, state) VALUES ('Projector', 'Good');
INSERT INTO Implement (implementName, state) VALUES ('Whiteboard', 'Excellent');
INSERT INTO Implement (implementName, state) VALUES ('Sound System', 'Fair');

-- Assigning implements to rooms
INSERT INTO RoomImplement (implementId, roomId) VALUES (1, 211012);
INSERT INTO RoomImplement (implementId, roomId) VALUES (2, 202010);
INSERT INTO RoomImplement (implementId, roomId) VALUES (3, 182181);
INSERT INTO RoomImplement (implementId, roomId) VALUES (2, 182181);

-- Inserting reservation states
INSERT INTO ReservationState (description) VALUES ('Refused');
INSERT INTO ReservationState (description) VALUES ('Accepted');
INSERT INTO ReservationState (description) VALUES ('In revision');

-- Inserting reservations with date and time, separated by exactly two hours
INSERT INTO Reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Board Meeting', 'Monthly board meeting', '2023-10-25 14:30:00', '2023-10-25 16:30:00', 0, 1, 211012, 1234567);
INSERT INTO Reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Training Session', 'New employee training', '2023-10-28 09:00:00', '2023-10-28 11:00:00', 1, 2, 182181, 246800);
INSERT INTO Reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Team Meeting', 'Weekly team meeting', '2023-10-27 15:00:00', '2023-10-27 17:00:00', 1, 3, 202010, 246800);