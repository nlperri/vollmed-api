package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentDetailsDTO;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import med.voll.api.domain.appointment.ScheduleAppointmentService;
import med.voll.api.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleAppointmentDTO> scheduleAppointmentJsonRequest;

    @Autowired
    private JacksonTester<AppointmentDetailsDTO> scheduleAppointmentJsonResponse;

    @MockBean
    private ScheduleAppointmentService scheduleAppointment;

    @Test
    @WithMockUser
    @DisplayName("It should return 400 http status code when receive invalid information")
    void schedule_scenarioOne() throws Exception {
        var response = mvc.perform(post("/appointments"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    @DisplayName("It should return 200 http status code when receive valid information")
    void schedule_scenarioTwo() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        var appointmentDetails = new AppointmentDetailsDTO(null, 1L, 1L, date);

        when(scheduleAppointment.execute(any())).thenReturn(appointmentDetails);


        var response = mvc.perform(
                post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(scheduleAppointmentJsonRequest.write(
                                new ScheduleAppointmentDTO(1L, 1L, date, specialty)
                        ).getJson()
                        )
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var responseExpectation = scheduleAppointmentJsonResponse.write(
                appointmentDetails
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(responseExpectation);
    }
}