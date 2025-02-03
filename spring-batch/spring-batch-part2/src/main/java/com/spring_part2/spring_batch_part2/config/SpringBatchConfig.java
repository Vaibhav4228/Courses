package com.spring_part2.spring_batch_part2.config;

import com.spring_part2.spring_batch_part2.entity.Employee;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public FlatFileItemReader<Employee> reader(){
        FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/employee_data.csv"));
        itemReader.setName("employeeReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }
    private LineMapper<Employee> lineMapper() {
        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "name", "username", "gender", "salary");

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }


}
