package com.school.schoolsystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "students")
@AttributeOverride(name = "id", column = @Column(name = "student_id"))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student extends Person {

    @Column(name = "parent_contact")
    private String parentContact;

    @ManyToMany
    @JoinTable(
            name = "student_class",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "class_id")
    )
    private Set<SchoolClass> classes;

    @OneToMany(mappedBy = "student")
    private Set<Grade> grades;

}
