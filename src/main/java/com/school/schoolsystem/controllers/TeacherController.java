package com.school.schoolsystem.controllers;

import com.school.schoolsystem.models.Teacher;
import com.school.schoolsystem.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@CrossOrigin("http://localhost:3000")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/count")
    public Long getNumberTeachers() {
        return teacherService.countTeachers();
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Teacher>> getAllTeachersSorted() {
        return ResponseEntity.ok(teacherService.getAllTeachersSorted());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        var teacher = teacherService.getTeacherById(id);
        return teacher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
        var teacher = teacherService.getTeacherById(id);
        if (teacher.isPresent()) {
            teacherDetails.setId(id);
            return ResponseEntity.ok(teacherService.updateTeacher(teacherDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        var teacher = teacherService.getTeacherById(id);
        if (teacher.isPresent()) {
            teacherService.deleteTeacher(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
