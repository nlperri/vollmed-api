package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record ScheduleAppointmentDTO(
        @JsonAlias("doctor_id")
        Long doctorId,

        @NotNull
        @JsonAlias("patient_id")
        Long patientId,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime date,

        Specialty specialty
) {
}
