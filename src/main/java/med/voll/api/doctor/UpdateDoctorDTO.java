package med.voll.api.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.address.AddressDTO;

public record UpdateDoctorDTO (
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressDTO endereco
) {
}
