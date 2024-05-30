package com.school.schoolsystem.controllers;

import com.school.schoolsystem.models.SchoolClass;
import com.school.schoolsystem.models.Student;
import com.school.schoolsystem.models.Teacher;
import com.school.schoolsystem.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin("http://localhost:3000")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping
    public ResponseEntity<List<SchoolClass>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolClass> getClassById(@PathVariable Long id) {
        var classEntity = classService.getClassById(id);
        return classEntity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SchoolClass> createClass(@RequestBody SchoolClass schoolClass) throws URISyntaxException {
        var result = classService.saveClass(schoolClass);
        return ResponseEntity.created(new URI("/api/classes/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolClass> updateClass(@PathVariable Long id, @RequestBody SchoolClass classDetails) {
        var classEntity = classService.getClassById(id);
        if (classEntity.isPresent()) {
            classDetails.setId(id);
            return ResponseEntity.ok(classService.updateClass(classDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClass(@PathVariable Long id) {
        var classEntity = classService.getClassById(id);
        if (classEntity.isPresent()) {
            classService.deleteClass(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
