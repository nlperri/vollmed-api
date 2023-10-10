package med.voll.api.doctor;

public record FetchDoctorsDTO (
  Long id,
  String nome,
  String email,
  String crm,
  Specialty especialidade
){
    public FetchDoctorsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
    }

}
