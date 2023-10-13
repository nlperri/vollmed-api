package med.voll.api.patient;

import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDetailsDTO> create(
            @RequestBody @Valid CreatePatientDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        var patient = new Patient(data);

        repository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailsDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<FetchPatientsDTO>> fetch(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        var patients = repository.findAllByAtivoTrue(pagination).map(FetchPatientsDTO::new);

        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailsDTO> getById(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new PatientDetailsDTO(patient));
    }

    @PutMapping
    public ResponseEntity<PatientDetailsDTO> update(@RequestBody @Valid UpdatePatientDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.update(data);

        return ResponseEntity.ok(new PatientDetailsDTO(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }
}

