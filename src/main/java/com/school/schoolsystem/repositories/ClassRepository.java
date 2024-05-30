package com.school.schoolsystem.repositories;

import com.school.schoolsystem.models.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<SchoolClass, Long> {
    SchoolClass findByClassName(String className);
}
