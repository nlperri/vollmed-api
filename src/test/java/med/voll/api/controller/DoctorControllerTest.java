package med.voll.api.controller;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.doctor.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CreateDoctorDTO> createDoctorJsonRequest;

    @Autowired
    private JacksonTester<DoctorDetailsDTO> createDoctorJsonResponse;

    @MockBean
    private DoctorRepository repository;

    @Test
    @DisplayName("It should return 400 http status code when receive invalid information")
    @WithMockUser
    void create_scenarioOne() throws Exception {

        var response = mvc.perform(post("/doctors"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return 200 http status code when receive valid information")
    @WithMockUser
    void create_scenarioTwo() throws Exception {

        var doctor = new CreateDoctorDTO(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Specialty.CARDIOLOGIA,
                addressMock()
        );

        when(repository.save(any())).thenReturn(new Doctor(doctor));

        var response = mvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createDoctorJsonRequest.write(doctor).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var doctorDetails = new DoctorDetailsDTO(
                null,
                doctor.name(),
                doctor.email(),
                doctor.crm(),
                doctor.phone(),
                doctor.specialty(),
                new Address(doctor.address())
        );

        var responseExpectation = createDoctorJsonResponse.write(doctorDetails).getJson();

        assertThat(response.getContentAsString()).isEqualTo(responseExpectation);

    }

    private AddressDTO addressMock() {
        return new AddressDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}