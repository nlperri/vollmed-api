package med.voll.api.controller;

import med.voll.api.address.Address;
import med.voll.api.doctor.CreateDoctorDTO;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    public void createDoctor(@RequestBody CreateDoctorDTO data) {
        repository.save(new Doctor(data));
    }
}
