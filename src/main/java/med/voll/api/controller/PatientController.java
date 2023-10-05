package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientDTO;
import med.voll.api.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void createPatient(@RequestBody @Valid PatientDTO data) {
        repository.save(new Patient(data));
    }
}
