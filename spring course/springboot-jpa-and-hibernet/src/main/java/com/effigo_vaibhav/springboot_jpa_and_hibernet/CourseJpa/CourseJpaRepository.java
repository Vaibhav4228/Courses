package com.effigo_vaibhav.springboot_jpa_and_hibernet.CourseJpa;

import com.effigo_vaibhav.springboot_jpa_and_hibernet.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Long> {

}
