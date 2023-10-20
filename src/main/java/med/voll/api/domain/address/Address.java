package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String place;
    private String neighborhood;
    private String cep;
    private String number;
    private String complement;
    private String city;
    private String uf;

    public Address(AddressDTO data) {
        this.place = data.place();
        this.neighborhood = data.neighborhood();
        this.cep = data.cep();
        this.number = data.number();
        this.complement = data.complement();
        this.city = data.city();
        this.uf = data.uf();
    }

    public Address update(AddressDTO data) {
        return new Address(
                data.place() != null ? data.place() : this.place,
                data.neighborhood() != null ? data.neighborhood() : this.neighborhood,
                data.cep() != null ? data.cep() : this.cep,
                data.number() != null ? data.number() : this.number,
                data.complement() != null ? data.complement() : this.complement,
                data.city() != null ? data.city() : this.city,
                data.uf() != null ? data.uf() : this.uf
        );
    }
}
