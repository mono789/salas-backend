-- TABLES
CREATE TABLE Customer (
                          customerId int NOT NULL,
                          customerName varchar(64) NOT NULL,
                          lastName varchar(64) NOT NULL,
                          email varchar(64) NOT NULL,
                          roleId int NOT NULL,
                          PRIMARY KEY (customerId),
                          CONSTRAINT uc_Customer_email UNIQUE (email)
);

CREATE TABLE Role (
                      roleId SERIAL PRIMARY KEY,
                      roleName varchar(64) NOT NULL,
                      CONSTRAINT uc_RoleName UNIQUE (roleName)
);

CREATE TABLE Room (
                      roomId SERIAL PRIMARY KEY,
                      computerAmount int NOT NULL,
                      building int NOT NULL,
                      roomNum int NOT NULL,
                      roomName varchar(32),
                      subRoom int NOT NULL
);

CREATE TABLE ReservationState (
                                  reservationStateId SERIAL PRIMARY KEY,
                                  description varchar(20) NOT NULL
);

CREATE TABLE Reservation (
                             reservationId SERIAL PRIMARY KEY,
                             activityName varchar(64) NOT NULL,
                             activityDescription varchar(512) NOT NULL,
                             startsAt timestamp NOT NULL,
                             endsAt timestamp NOT NULL,
                             reservationType int NOT NULL,
                             roomId int NOT NULL,
                             reservationStateId int,
                             customerId varchar(64) NOT NULL,
                             FOREIGN KEY (reservationStateId) REFERENCES ReservationState (reservationStateId),
                             FOREIGN KEY (roomId) REFERENCES Room (roomId),
                             FOREIGN KEY (customerId) REFERENCES Customer (customerId)
);

CREATE TABLE Application (
                             applicationId SERIAL PRIMARY KEY,
                             applicationName varchar(64) NOT NULL,
                             version varchar(16) NOT NULL
);

CREATE TABLE RoomSoftware (
                              applicationId int NOT NULL,
                              roomId int NOT NULL,
                              FOREIGN KEY (applicationId) REFERENCES Application (applicationId),
                              FOREIGN KEY (roomId) REFERENCES Room (roomId)
);

CREATE TABLE Restriction (
                             restrictionId SERIAL PRIMARY KEY,
                             description varchar(128) NOT NULL
);

CREATE TABLE RoomRestriction (
                                 restrictionId int NOT NULL,
                                 roomId int NOT NULL,
                                 FOREIGN KEY (restrictionId) REFERENCES Restriction (restrictionId),
                                 FOREIGN KEY (roomId) REFERENCES Room (roomId)
);

CREATE TABLE Implement (
                           implementId SERIAL PRIMARY KEY,
                           implementName varchar(32) NOT NULL,
                           state varchar(32) NOT NULL
);

CREATE TABLE RoomImplement (
                               implementId int NOT NULL,
                               roomId int NOT NULL,
                               FOREIGN KEY (implementId) REFERENCES Implement (implementId),
                               FOREIGN KEY (roomId) REFERENCES Room (roomId)
);

-- CONSTRAINTS
ALTER TABLE Customer ADD CONSTRAINT fk_CustomerRoleId FOREIGN KEY (roleId) REFERENCES Role (roleId);

ALTER TABLE Reservation ADD CONSTRAINT fk_ReservStateId FOREIGN KEY (reservationStateId) REFERENCES ReservationState (reservationStateId);

ALTER TABLE Reservation ADD CONSTRAINT fk_ReservRoomId FOREIGN KEY (roomId) REFERENCES Room (roomId);

ALTER TABLE Reservation ADD CONSTRAINT fk_ReservCustomerId FOREIGN KEY (customerId) REFERENCES Customer (customerId);

ALTER TABLE RoomSoftware ADD CONSTRAINT fk_RoomSoftwareId FOREIGN KEY (applicationId) REFERENCES Application (applicationId);

ALTER TABLE RoomSoftware ADD CONSTRAINT fk_SoftwareId FOREIGN KEY (roomId) REFERENCES Room (roomId);

ALTER TABLE RoomRestriction ADD CONSTRAINT fk_RoomRestrictionId FOREIGN KEY (restrictionId) REFERENCES Restriction (restrictionId);

ALTER TABLE RoomRestriction ADD CONSTRAINT fk_RestrictionId FOREIGN KEY (roomId) REFERENCES Room (roomId);

ALTER TABLE RoomImplement ADD CONSTRAINT fk_RoomImplementId FOREIGN KEY (implementId) REFERENCES Implement (implementId);

ALTER TABLE RoomImplement ADD CONSTRAINT fk_ImplementId FOREIGN KEY (roomId) REFERENCES Room (roomId);