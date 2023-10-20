package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailsDTO> create(
            @RequestBody @Valid CreateDoctorDTO data,
            UriComponentsBuilder uriBuilder
    ) {
        var doctor = new Doctor(data);

        repository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<FetchDoctorsDTO>> fetch(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var doctors = repository.findAllByActiveTrue(pagination).map(FetchDoctorsDTO::new);

        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDTO> getById(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailsDTO> update(@RequestBody @Valid UpdateDoctorDTO data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.update(data);

        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }


}
