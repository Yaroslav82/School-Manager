package com.hillel.multi.service.mappers;

import com.hillel.model.UserDTO;
import com.hillel.multi.persistent.entities.Role;
import com.hillel.multi.persistent.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToDto(User user);

    List<UserDTO> usersToDto(List<User> users);

    List<String> rolesToString(Set<Role> value);

    default String roleToString(Role role) {
        return role != null ? role.getAuthority() : null;
    }
}
