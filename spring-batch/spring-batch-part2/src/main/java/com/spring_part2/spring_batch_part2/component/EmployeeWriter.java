package com.spring_part2.spring_batch_part2.component;

import com.spring_part2.spring_batch_part2.entity.Employee;
import com.spring_part2.spring_batch_part2.repo.EmployeeRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmployeeWriter implements ItemWriter<Employee> {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void write(Chunk<? extends Employee> chunk) throws Exception {
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        employeeRepository.saveAll(chunk.getItems());
    }

}
