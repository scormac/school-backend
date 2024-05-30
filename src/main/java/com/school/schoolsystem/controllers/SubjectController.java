package com.school.schoolsystem.controllers;

import com.school.schoolsystem.dto.SubjectRequest;
import com.school.schoolsystem.models.SchoolClass;
import com.school.schoolsystem.models.Subject;
import com.school.schoolsystem.services.ClassService;
import com.school.schoolsystem.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin("http://localhost:3000")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClassService classService;

    @GetMapping("/count")
    public Long getNumberSubjects() {
        return subjectService.countSubject();
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        var subject = subjectService.getSubjectById(id);
        return subject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) throws URISyntaxException {
        var result = subjectService.saveSubject(subject);
        return ResponseEntity.created(new URI("/api/subjects/" + result.getId())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject subjectDetails) {
        var subject = subjectService.getSubjectById(id);
        if (subject.isPresent()) {
            subjectDetails.setId(id);
            return ResponseEntity.ok(subjectService.updateSubject(subjectDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
       var subject = subjectService.getSubjectById(id);
        if (subject.isPresent()) {
            subjectService.deleteSubject(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Subject> addSubject(@RequestBody SubjectRequest subjectRequest) {
        Set<SchoolClass> classes = new HashSet<>();
        for (String className : subjectRequest.getClassNames()) {
            SchoolClass schoolClass = classService.findByClassName(className);
            if (schoolClass != null) {
                classes.add(schoolClass);
            }
        }
        Subject subject = new Subject();
        subject.setSubjectName(subjectRequest.getSubjectName());
        subject.setClasses(classes);

        Subject savedSubject = subjectService.saveSubject(subject);

        for (SchoolClass schoolClass : classes) {
            schoolClass.getSubjects().add(savedSubject);
            classService.saveClass(schoolClass);
        }

        return ResponseEntity.ok(savedSubject);
    }
}



