package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<FetchDoctorsDTO> fetch(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        return repository.findAllByAtivoTrue(pagination).map(FetchDoctorsDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctorDTO data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }
}
