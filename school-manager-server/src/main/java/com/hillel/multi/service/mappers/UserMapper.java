package com.hillel.multi.service.mappers;

import com.hillel.model.UserDTO;
import com.hillel.multi.persistent.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToDto(User user);

    @Mapping(target = "password", ignore = true)
    User dtoToUser(UserDTO userDTO);

    List<UserDTO> usersToDto(List<User> users);
}
