package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import med.voll.api.domain.address.AddressDTO;

public record UpdatePatientDTO(
        Long id,
        String nome,
        String telefone,
        @Valid AddressDTO endereco
        ) {
}
