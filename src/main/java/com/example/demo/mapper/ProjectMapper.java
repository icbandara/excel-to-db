package com.example.demo.mapper;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author : Thushan Kavinda <tkavinda@controlunion.com>
 * @since : 12/12/13/2023/2023
 **/
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectDTO e);
    @Mapping(target = "processingUnits", ignore = true)
    ProjectDTO toProjectDTO(Project e);
}
