package com.spring.dao;

import com.spring.model.Group;
import com.spring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
