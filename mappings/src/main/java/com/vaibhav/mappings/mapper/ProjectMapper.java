package com.vaibhav.mappings.mapper;

import com.vaibhav.mappings.dto.ProjectDTO;
import com.vaibhav.mappings.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO projectToProjectDTO(Project project);
}

