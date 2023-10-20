package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import med.voll.api.domain.address.AddressDTO;

public record UpdatePatientDTO(
        Long id,
        String name,
        String phone,
        @Valid AddressDTO address
        ) {
}
