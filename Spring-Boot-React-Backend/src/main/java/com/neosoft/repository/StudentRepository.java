package com.neosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
