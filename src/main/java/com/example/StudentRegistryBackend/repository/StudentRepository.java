// Interacts with logic
package com.example.StudentRegistryBackend.repository;

import com.example.StudentRegistryBackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
