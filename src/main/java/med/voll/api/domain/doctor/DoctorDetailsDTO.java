package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DoctorDetailsDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Specialty especialidade,
        Address endereco
) {

    public DoctorDetailsDTO (Doctor doctor) {
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getTelefone(), doctor.getEspecialidade(), doctor.getEndereco() );
    }
}
