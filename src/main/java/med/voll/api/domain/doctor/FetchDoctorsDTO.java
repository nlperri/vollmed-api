package med.voll.api.domain.doctor;

public record FetchDoctorsDTO (
  Long id,
  String name,
  String email,
  String crm,
  Specialty specialty
){
    public FetchDoctorsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
