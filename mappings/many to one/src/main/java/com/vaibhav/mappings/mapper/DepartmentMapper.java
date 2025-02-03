package com.vaibhav.mappings.mapper;

import com.vaibhav.mappings.dto.DepartmentDTO;
import com.vaibhav.mappings.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO departmentToDepartmentDTO(Department department);
}
