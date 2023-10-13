package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDTO;

public record UpdateDoctorDTO (
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressDTO endereco
) {
}
