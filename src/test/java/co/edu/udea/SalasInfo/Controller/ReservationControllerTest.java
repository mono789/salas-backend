package co.edu.udea.SalasInfo.Controller;

import co.edu.udea.SalasInfo.Model.Reservation;
import co.edu.udea.SalasInfo.Service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ReservationService reservationService;


    @BeforeEach
    void setUp() {
       // mockMvc= MockMvcBuilder.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void findAll() {
    }

    @Test
    void freeAll() {
    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}