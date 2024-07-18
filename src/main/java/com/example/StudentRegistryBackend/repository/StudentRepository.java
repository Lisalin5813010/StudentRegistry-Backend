// Interacts with logic
package com.example.StudentRegistryBackend.repository;
import com.example.StudentRegistryBackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
  Student findByNameContainingIgnoreCase(String name);
  Student findByName(String name);

  Student findByNameAndPassword(String name, String password);


}