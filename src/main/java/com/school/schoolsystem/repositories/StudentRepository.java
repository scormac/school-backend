package com.school.schoolsystem.repositories;

import com.school.schoolsystem.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
