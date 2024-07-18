// Contains business logic
package com.example.StudentRegistryBackend.service;


import com.example.StudentRegistryBackend.common.JwtTokenUtils;
import com.example.StudentRegistryBackend.exception.CustomException;
import com.example.StudentRegistryBackend.model.Student;
import com.example.StudentRegistryBackend.model.Params;
import com.example.StudentRegistryBackend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student findByName(String name){
        return studentRepository.findByName(name);
    }

    public void add(Student student) {
        // 1. 用户名一定要有，否则不让新增（后面需要用户名登录）
        if (student.getName() == null || "".equals(student.getName())) {

            throw new CustomException("用户名不能为空");
        }
        // 2. 进行重复性判断，同一名字的管理员不允许重复新增：只要根据用户名去数据库查询一下就可以了
        Student user = studentRepository.findByName(student.getName());
        if (user != null) {
            // 说明已经有了，这里我们就要提示前台不允许新增了
            throw new CustomException("该用户名已存在，请更换用户名");
        }
        // 初始化一个密码
        if (student.getPassword() == null) {
            student.setPassword("123456");
        }
        studentRepository.save(student);
    }
    public void update(Student student) {
        studentRepository.save(student);
    }

    public Student findByById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    public void deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
        } else {
            throw new CustomException("Student not found");
        }
    }


    public Student login(Student student){
        //1. 进行非空判断
        if(student.getName() == null || "".equals(student.getName())){
            throw new CustomException("Name cannot be empty");
        }
        if(student.getPassword() == null || "".equals(student.getPassword())){
            throw new CustomException("密码不能为空");
        }
        //2. 进行数据库查询
        //如果没有查询到，返回null
        //如果查询到了，返回查询到的对象
        Student user = studentRepository.findByNameAndPassword(student.getName(), student.getPassword());
        if (user != null){
            String token = JwtTokenUtils.genToken(user.getId().toString(), user.getPassword());
            user.setToken(token);
            return user;
        }
        else {
            throw new CustomException("用户名或密码错误");
        }
    }
}