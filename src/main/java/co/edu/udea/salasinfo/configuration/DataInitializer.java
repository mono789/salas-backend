package co.edu.udea.salasinfo.configuration;

import co.edu.udea.salasinfo.model.*;
import co.edu.udea.salasinfo.repository.*;
import co.edu.udea.salasinfo.utils.enums.ImplementCondition;
import co.edu.udea.salasinfo.utils.enums.RStatus;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
import co.edu.udea.salasinfo.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ApplicationRepository applicationRepository;
    private final RestrictionRepository restrictionRepository;
    private final ImplementRepository implementRepository;
    private final ReservationStateRepository reservationStateRepository;
    private final ReservationRepository reservationRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            // Inserting roles
            Role roleAdmin = new Role(null, RoleName.ADMIN);
            Role roleUser = new Role(null, RoleName.USER);
            Role roleManager = new Role(null, RoleName.PROFESSOR);
            roleRepository.saveAll(Arrays.asList(roleAdmin, roleUser, roleManager));

            // Inserting customers
            User user1 = new User(null, "123456", "John", "Doe", "john.doe@example.com", "password1", roleAdmin);
            User user2 = new User(null, "789012","Jane", "Smith", "jane.smith@example.com", "password1", roleUser);
            User user3 = new User(null, "345678", "Bob", "Johnson", "bob.johnson@example.com", "oo", roleManager);
            userRepository.saveAll(Arrays.asList(user1, user2, user3));

            // Inserting rooms
            Room room1 = Room.builder()
                    .id(211012L)
                    .computerAmount(20)
                    .building("21")
                    .roomNum("101")
                    .roomName("Conference Room 1")
                    .subRoom(2)
                    .build();

            Room room2 = Room.builder()
                    .id(202010L)
                    .computerAmount(30)
                    .building("20")
                    .roomNum("201")
                    .roomName("Meeting Room 1")
                    .subRoom(0)
                    .build();

            Room room3 = Room.builder()
                    .id(182101L)
                    .computerAmount(15)
                    .building("18")
                    .roomNum("210")
                    .roomName("LIS - Sala 1")
                    .subRoom(1)
                    .build();
            roomRepository.saveAll(Arrays.asList(room1, room2, room3));

            // Inserting applications
            Application app1 = new Application(null, "Microsoft Office", "2021", null);
            Application app2 = new Application(null, "Zoom", "5.0", null);
            Application app3 = new Application(null, "Adobe Photoshop", "2022", null);
            applicationRepository.saveAll(Arrays.asList(app1, app2, app3));

            // Assigning applications to rooms
            room1.setSoftware(List.of(app1));
            room2.setSoftware(List.of(app2));
            room3.setSoftware(List.of(app3));
            roomRepository.saveAll(Arrays.asList(room1, room2, room3));

            // Inserting restrictions
            Restriction restriction1 = new Restriction(null, "No food or drinks allowed", null);
            Restriction restriction2 = new Restriction(null, "No smoking in the room", null);
            Restriction restriction3 = new Restriction(null, "No pets allowed", null);
            restrictionRepository.saveAll(Arrays.asList(restriction1, restriction2, restriction3));

            // Assigning restrictions to rooms
            room1.setRestrictions(List.of(restriction1));
            room2.setRestrictions(List.of(restriction2));
            room3.setRestrictions(List.of(restriction3));
            roomRepository.saveAll(Arrays.asList(room1, room2, room3));

            // Inserting implements
            Implement implement1 = new Implement(null, "Projector", ImplementCondition.GOOD, null);
            Implement implement2 = new Implement(null, "Whiteboard", ImplementCondition.BROKEN, null);
            Implement implement3 = new Implement(null, "Sound System", ImplementCondition.BAD, null);
            implementRepository.saveAll(Arrays.asList(implement1, implement2, implement3));

            // Assigning implements to rooms
            room1.setImplementList(List.of(implement1));
            room2.setImplementList(List.of(implement2));
            room3.setImplementList(List.of(implement2));
            roomRepository.saveAll(Arrays.asList(room1, room2, room3));

            // Inserting reservation states
            ReservationState state1 = new ReservationState(null, RStatus.ACCEPTED);
            ReservationState state2 = new ReservationState(null, RStatus.PENDING);
            ReservationState state3 = new ReservationState(null, RStatus.REJECTED);
            reservationStateRepository.saveAll(Arrays.asList(state1, state2, state3));

            // Inserting reservations
            Reservation reservation1 = Reservation.builder()
                    .activityName("Board Meeting")
                    .activityDescription("Monthly board meeting")
                    .startsAt(LocalDateTime.of(2023, 10, 25, 14, 30))
                    .endsAt(LocalDateTime.of(2023, 10, 25, 16, 30))
                    .type(ReservationType.ONCE)
                    .room(room1)
                    .user(user1)
                    .reservationState(state1)
                    .build();

            Reservation reservation2 = Reservation.builder()
                    .activityName("Training Session")
                    .activityDescription("New employee training")
                    .startsAt(LocalDateTime.of(2023, 10, 28, 9, 0))
                    .endsAt(LocalDateTime.of(2023, 10, 28, 11, 0))
                    .type(ReservationType.WEEKLY)
                    .room(room3)
                    .user(user2)
                    .reservationState(state1)
                    .build();

            Reservation reservation3 = Reservation.builder()
                    .activityName("Team Meeting")
                    .activityDescription("Weekly team meeting")
                    .startsAt(LocalDateTime.of(2023, 10, 27, 15, 0))
                    .endsAt(LocalDateTime.of(2023, 10, 27, 17, 0))
                    .type(ReservationType.WEEKLY)
                    .room(room2)
                    .user(user3)
                    .reservationState(state1)
                    .build();

            reservationRepository.saveAll(Arrays.asList(reservation1, reservation2, reservation3));
        };
    }
}
