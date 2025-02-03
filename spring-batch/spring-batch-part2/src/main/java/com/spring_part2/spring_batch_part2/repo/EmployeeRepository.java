package com.spring_part2.spring_batch_part2.repo;

import com.spring_part2.spring_batch_part2.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
