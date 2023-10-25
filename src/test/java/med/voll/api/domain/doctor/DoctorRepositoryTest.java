package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressDTO;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.CreatePatientDTO;
import med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("It should return null when the one registered doctor is not available on date")
    void chooseRandomDoctorAvailableOnDate_scenarioOne() {
        var nextMondayAtTen = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctor = createDoctor("Medico", "medico@voll.med", "123456", Specialty.CARDIOLOGIA);
        var patient = createPatient("Paciente", "paciente@email.com", "0000000000");
         createAppointment(doctor, patient, nextMondayAtTen);

        var availableDoctor = doctorRepository.chooseRandomDoctorAvailableOnDate(Specialty.CARDIOLOGIA, nextMondayAtTen);

        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("It should return doctor when its available on date")
    void chooseRandomDoctorAvailableOnDate_scenarioTwo() {
        var nextMondayAtTen = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctor = createDoctor("Medico", "medico@voll.med", "123456", Specialty.CARDIOLOGIA);

        var availableDoctor = doctorRepository.chooseRandomDoctorAvailableOnDate(Specialty.CARDIOLOGIA, nextMondayAtTen);

        assertThat(availableDoctor).isEqualTo(doctor);
    }


    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date));
    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorMock(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String cpf) {
        var patient = new Patient(patientMock(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private CreateDoctorDTO doctorMock(String name, String email, String crm, Specialty specialty) {
        return new CreateDoctorDTO(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressMock()
        );
    }

    private CreatePatientDTO patientMock(String name, String email, String cpf) {
        return new CreatePatientDTO(
                name,
                email,
                "61999999999",
                cpf,
                addressMock()
        );
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