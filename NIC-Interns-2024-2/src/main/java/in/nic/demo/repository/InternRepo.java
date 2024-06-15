package in.nic.demo.repository;

import in.nic.demo.entity.Intern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternRepo extends JpaRepository<Intern, Long> {
    // Implement custom methods for your requirements
}
