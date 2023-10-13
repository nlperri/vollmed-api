package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CreatePatientDTO data) {
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<FetchPatientsDTO> fetch(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        return repository.findAllByAtivoTrue(pagination).map(FetchPatientsDTO::new);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdatePatientDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.update(data);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
    }
}
