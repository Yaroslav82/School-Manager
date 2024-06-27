package com.hillel.multi.service.mappers;

import com.hillel.model.HomeworkDTO;
import com.hillel.multi.persistent.entities.Homework;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    HomeworkMapper INSTANCE = Mappers.getMapper(HomeworkMapper.class);

    @Mapping(source = "groupName", target = "group")
    HomeworkDTO homeworkToDto(Homework homework);

    @Mapping(source = "group", target = "groupName")
    Homework dtoToHomework(HomeworkDTO homeworkDTO);

    List<HomeworkDTO> homeworksToDto(List<Homework> homeworks);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "description", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "group", target = "groupName", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subject", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "deadline", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIntoHomework(@MappingTarget Homework homework, HomeworkDTO homeworkDTO);
}
