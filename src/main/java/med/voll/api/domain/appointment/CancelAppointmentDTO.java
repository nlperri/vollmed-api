package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record CancelAppointmentDTO(
        @NotNull
        Long appointmentId,

        @NotNull
        ReasonForCancellation reason
) {
}
