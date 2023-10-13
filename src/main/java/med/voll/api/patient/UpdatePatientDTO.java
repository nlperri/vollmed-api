package med.voll.api.patient;

import jakarta.validation.Valid;
import med.voll.api.address.AddressDTO;

public record UpdatePatientDTO(
        Long id,
        String nome,
        String telefone,
        @Valid AddressDTO endereco
        ) {
}
