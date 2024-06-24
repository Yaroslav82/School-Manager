package com.hillel.multi.service.mappers;

import com.hillel.model.LessonDTO;
import com.hillel.multi.persistent.entities.Lesson;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    @Mapping(source = "groupName", target = "group")
    LessonDTO lessonToDto(Lesson lesson);

    @Mapping(source = "group", target = "groupName")
    Lesson dtoToLesson(LessonDTO lessonDTO);

    List<LessonDTO> lessonsToDto(List<Lesson> lessons);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "group", target = "groupName", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "subject", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIntoLesson(@MappingTarget Lesson lesson, LessonDTO lessonDTO);
}
