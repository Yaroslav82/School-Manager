package com.hillel.multi.service.mappers;

import com.hillel.model.StudentDTO;
import com.hillel.multi.persistent.entities.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "groupName", target = "group")
    StudentDTO studentToDto(Student student);

    @Mapping(source = "group", target = "groupName")
    Student dtoToStudent(StudentDTO studentDTO);

    List<StudentDTO> studentsToDto(List<Student> students);

    @InheritConfiguration
    @Mapping(source = "id", target = "id", ignore = true)
    @Mapping(source = "firstName", target = "firstName", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "lastName", target = "lastName", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "group", target = "groupName", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateIntoStudent(@MappingTarget Student student, StudentDTO studentDTO);
}
