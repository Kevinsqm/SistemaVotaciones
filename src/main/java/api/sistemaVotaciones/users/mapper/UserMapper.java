package api.sistemaVotaciones.users.mapper;

import api.sistemaVotaciones.generics.GenericMapper;
import api.sistemaVotaciones.users.DTOs.UserDto;
import api.sistemaVotaciones.users.DTOs.UserRequesDto;
import api.sistemaVotaciones.users.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserDto, UserRequesDto, UserRequesDto, User> {
}
