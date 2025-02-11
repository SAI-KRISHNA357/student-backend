package com.assignment.student.Service;

import com.assignment.student.Repository.StudentRepository;
import com.assignment.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String SaveStudent(Student student) {

        studentRepository.save(student);
        return "SUCCESS";

    }

    public List<Student> getStudentList() {

        return studentRepository.findAll();
    }

    public String UpdateStudent(Student student) {

        if(null == student.getId() || !studentRepository.existsById(student.getId())) {

            return "Failure";
        }

        studentRepository.save(student);
        return "SUCCESS";
    }

    public boolean deleteStudentById(long id) {

        studentRepository.deleteById(id);
        return true;
    }
}
