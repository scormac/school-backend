package com.school.schoolsystem.services;

import com.school.schoolsystem.models.SchoolClass;
import com.school.schoolsystem.models.Student;
import com.school.schoolsystem.models.Teacher;
import com.school.schoolsystem.repositories.ClassRepository;
import com.school.schoolsystem.repositories.StudentRepository;
import com.school.schoolsystem.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public List<SchoolClass> getAllClasses() {
        return classRepository.findAll();
    }

    public Optional<SchoolClass> getClassById(Long id) {
        return classRepository.findById(id);
    }

    public SchoolClass saveClass(SchoolClass classEntity) {
        return classRepository.save(classEntity);
    }

    public void deleteClass(Long id) {
        classRepository.deleteById(id);
    }

    public SchoolClass updateClass(SchoolClass schoolClass) {
        return classRepository.save(schoolClass);
    }

    public SchoolClass findByClassName(String className) {
        return classRepository.findByClassName(className);
    }
}
