package in.nic.demo.controller;

import in.nic.demo.entity.Intern;
import in.nic.demo.repository.InternRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interns")
public class InternController {

    @Autowired
    private InternRepo internRepo;

    @GetMapping
    public List<Intern> getAllInterns() {
        return internRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Intern> getInternById(@PathVariable Long id) {
        return internRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Intern> updateIntern(@PathVariable Long id, @RequestBody Intern updatedIntern) {
        Intern existingIntern = internRepo.findById(id).orElse(null);

        if (existingIntern == null)
            return ResponseEntity.notFound().build();

        existingIntern.setName(updatedIntern.getName());
        existingIntern.setDepartment(updatedIntern.getDepartment());

        Intern savedIntern = internRepo.save(existingIntern);
        return ResponseEntity.ok(savedIntern);
    }

    @PostMapping
    public ResponseEntity<Intern> createIntern(@RequestBody Intern intern) {
        Intern savedIntern = internRepo.save(intern);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIntern);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIntern(@PathVariable("id") Long id) {
        internRepo.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
