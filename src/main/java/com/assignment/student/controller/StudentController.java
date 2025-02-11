package com.assignment.student.controller;

import com.assignment.student.Service.StudentService;
import com.assignment.student.model.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000") // CORS for the entire controller
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> saveStudent(@Valid @RequestBody Student student) {

        return new ResponseEntity<>(studentService.SaveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getStudents() {

        return studentService.getStudentList();
    }

    @PutMapping
    public ResponseEntity<String> updateStudent(@Valid @RequestBody Student student) {

        HttpStatus responseStatus = HttpStatus.OK;
        
        String updateResponse = studentService.UpdateStudent(student);
        System.out.println(student + " "+ updateResponse);
        if("Failure".equals(updateResponse)) {
            responseStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(updateResponse, responseStatus);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable("studentId") long studentId) {

        return new ResponseEntity<>(studentService.deleteStudentById(studentId), HttpStatus.OK);
    }
}
