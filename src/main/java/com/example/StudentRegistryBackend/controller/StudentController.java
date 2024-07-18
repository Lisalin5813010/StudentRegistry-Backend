// Handles HTTP requests
package com.example.StudentRegistryBackend.controller;

import com.example.StudentRegistryBackend.common.Result;
import com.example.StudentRegistryBackend.exception.CustomException;
import com.example.StudentRegistryBackend.model.Params;
import com.example.StudentRegistryBackend.model.Student;
import com.example.StudentRegistryBackend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/student")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result findAll() {
        //logger.info("拦截器拦截成功，正是调用接口内部，查询管理员信息");
        List<Student> students = studentService.getAllStudents();
        return Result.success(students);
    }
    @PostMapping("/register")
    public Result register(@RequestBody Student student) {

        studentService.add(student);
        return Result.success(student);
    }

    @GetMapping("/search")
    public Result findBySearch(@RequestParam String name) {
        Student student = studentService.findByName(name);
        return student != null ? Result.success(student) : Result.error("Student not founddddd");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        Student loginStudent = studentService.login(student);
        return Result.success(loginStudent);


    }
    @PostMapping
    public Result save(@RequestBody Student student) {
        if (student.getId() == null) {
            studentService.add(student);
        } else {
            studentService.update(student);
        }
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return Result.success("Student deleted successfully");
    }
}
