package com.assignment.student.controller;

import com.assignment.student.Service.StudentService;
import com.assignment.student.model.Student;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

        log.info("[Student-Controller] Save Student Request : {}", student);
        return new ResponseEntity<>(studentService.SaveStudent(student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> getStudents() {

        List<Student> studentList = studentService.getStudentList();
        log.info("[Student-Controller] Total Student List Size : {}",
                studentList.isEmpty() ? 0 : studentList.size());
        return studentList;
    }

    @PutMapping
    public ResponseEntity<String> updateStudent(@Valid @RequestBody Student student) {

        HttpStatus responseStatus = HttpStatus.OK;
        String updateResponse = studentService.UpdateStudent(student);
        if("Failure".equals(updateResponse)) {
            responseStatus = HttpStatus.BAD_REQUEST;
        }
        log.info("[Student-Controller] Update Student Request for : {} is : {}", student, updateResponse);
        return new ResponseEntity<>(updateResponse, responseStatus);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable("studentId") long studentId) {
        boolean isDeleted = studentService.deleteStudentById(studentId);
        log.info("[Student-Controller] Delete Student for : {} | is : {}", studentId, isDeleted);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
}
