package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private Boolean active;

    @Embedded
    private Address address;

    public Patient(CreatePatientDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.phone = data.phone();
        this.address = new Address(data.address());
        this.active = true;
    }

    public void update(UpdatePatientDTO data) {
        this.name = data.name() != null ? data.name() : this.name;
        this.phone = data.phone() != null ? data.phone() : this.phone;
        this.address = data.address() != null ? this.address.update(data.address()) : this.address;
    }

    public void delete() {
        this.active = false;
    }
}
