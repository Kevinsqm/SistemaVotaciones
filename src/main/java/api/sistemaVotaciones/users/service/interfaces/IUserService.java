package api.sistemaVotaciones.users.service.interfaces;


import api.sistemaVotaciones.generics.service.interfaces.IGenericService;
import api.sistemaVotaciones.security.TokenDto;
import api.sistemaVotaciones.users.DTOs.LoginDto;
import api.sistemaVotaciones.users.DTOs.UserDto;
import api.sistemaVotaciones.users.DTOs.UserRequesDto;
import api.sistemaVotaciones.users.model.User;

public interface IUserService extends IGenericService<UserDto, User, Long> {

    UserDto findByUsername (String username);
    void save(UserRequesDto userDto);
    TokenDto login(LoginDto loginDto);

}
