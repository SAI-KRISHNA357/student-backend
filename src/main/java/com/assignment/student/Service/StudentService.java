package com.assignment.student.Service;

import com.assignment.student.Repository.StudentRepository;
import com.assignment.student.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;

@Slf4j
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String SaveStudent(Student student) {

        student.setCreatedOn(LocalDateTime.now());
        student.setUpdatedOn(LocalDateTime.now());
        studentRepository.save(student);
        log.info("[Student-Service] Student Saved Successfully");
        return "SUCCESS";

    }

    public List<Student> getStudentList() {

        return studentRepository.findAll();
    }

    public String UpdateStudent(Student student) {

        if(null == student.getId() || !studentRepository.existsById(student.getId())) {
            log.info("[Student-Service] Student Id is invalid : {}", student.getId());
            return "Failure";
        }
        Student existingRecord = studentRepository.getReferenceById(student.getId());
        existingRecord.setName(student.getName());
        existingRecord.setAge(student.getAge());
        existingRecord.setClassName(student.getClassName());
        existingRecord.setMobile(student.getMobile());
        existingRecord.setUpdatedOn(LocalDateTime.now());
        studentRepository.save(existingRecord);
        return "SUCCESS";
    }

    public boolean deleteStudentById(long id) {

        studentRepository.deleteById(id);
        return true;
    }
}
