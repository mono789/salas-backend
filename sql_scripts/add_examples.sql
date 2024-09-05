-- Inserting roles
INSERT INTO Role (name) VALUES (0);
INSERT INTO Role (name) VALUES (1);
INSERT INTO Role (name) VALUES (2);
INSERT INTO Role (name) VALUES (3);


-- Inserting customers
INSERT INTO Customer (customerId, firstname, lastName, email, roleid, password)
VALUES (123456, 'John', 'Doe', 'john.doe@example.com', 1,'password1');
INSERT INTO Customer (customerId, firstname, lastName, email, roleid, password)
VALUES (789012, 'Jane', 'Smith', 'jane.smith@example.com', 2,'password1');
INSERT INTO Customer (customerId, firstname, lastName, email, roleid, password)
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
INSERT INTO Implement (implementName, state) VALUES ('Projector', 0);
INSERT INTO Implement (implementName, state) VALUES ('Whiteboard', 1);
INSERT INTO Implement (implementName, state) VALUES ('Sound System', 2);

-- Assigning implements to rooms
INSERT INTO RoomImplement (implementId, roomId) VALUES (1, 211012);
INSERT INTO RoomImplement (implementId, roomId) VALUES (2, 202010);
INSERT INTO RoomImplement (implementId, roomId) VALUES (2, 182181);

-- Inserting reservation states
INSERT INTO ReservationState (description) VALUES (0);
INSERT INTO ReservationState (description) VALUES (1);
INSERT INTO ReservationState (description) VALUES (2);

-- Inserting reservations with date and time, separated by exactly two hours
INSERT INTO Reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Board Meeting', 'Monthly board meeting', '2023-10-25 14:30:00', '2023-10-25 16:30:00', 0, 1, 211012, 123456);
INSERT INTO Reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Training Session', 'New employee training', '2023-10-28 09:00:00', '2023-10-28 11:00:00', 1, 2, 182181, 789012);
INSERT INTO Reservation (activityName, activityDescription, startsAt, endsAt, reservationType, reservationStateId, roomId, customerId)
VALUES ('Team Meeting', 'Weekly team meeting', '2023-10-27 15:00:00', '2023-10-27 17:00:00', 1, 3, 202010, 345678 );