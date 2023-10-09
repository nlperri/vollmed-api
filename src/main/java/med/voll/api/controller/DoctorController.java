package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.CreateDoctorDTO;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.FetchDoctorsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid CreateDoctorDTO data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    public List<FetchDoctorsDTO> fetch() {
        return repository.findAll().stream().map(FetchDoctorsDTO::new).toList();
    }
}
