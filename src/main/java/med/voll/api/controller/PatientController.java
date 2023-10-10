package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.FetchPatientsDTO;
import med.voll.api.patient.Patient;
import med.voll.api.patient.CreatePatientDTO;
import med.voll.api.patient.PatientRepository;
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
        return repository.findAll(pagination).map(FetchPatientsDTO::new);
    }
}
