// Contains business logic
package com.example.StudentRegistryBackend.service;

import com.example.StudentRegistryBackend.model.Student;
import com.example.StudentRegistryBackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        logger.info("Fetched {} students", students.size());
        return students;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}