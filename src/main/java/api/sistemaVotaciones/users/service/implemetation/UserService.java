package api.sistemaVotaciones.users.service.implemetation;

import api.sistemaVotaciones.exceptions.EntityNotFoundException;
import api.sistemaVotaciones.exceptions.UnauthenticatedException;
import api.sistemaVotaciones.generics.GenericRepository;
import api.sistemaVotaciones.generics.service.implementation.GenericService;
import api.sistemaVotaciones.roles.model.Role;
import api.sistemaVotaciones.roles.repository.RoleRepository;
import api.sistemaVotaciones.security.JwtService;
import api.sistemaVotaciones.security.TokenDto;
import api.sistemaVotaciones.users.DTOs.LoginDto;
import api.sistemaVotaciones.users.DTOs.UserDto;
import api.sistemaVotaciones.users.DTOs.UserRequesDto;
import api.sistemaVotaciones.users.mapper.UserMapper;
import api.sistemaVotaciones.users.model.User;
import api.sistemaVotaciones.users.repository.UserRepository;
import api.sistemaVotaciones.users.service.interfaces.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService extends GenericService<UserDto, User, Long, UserMapper> implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RoleRepository roleRepository;

    public UserService(GenericRepository<User, Long> genericRepository, UserMapper mapper, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, RoleRepository roleRepository) {
        super(genericRepository, mapper);
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("User with username: " + username + " not found"));
    }

    @Override
    @Transactional
    public void save(UserRequesDto userDto) {
        var hashPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(hashPassword);
        var role = roleRepository.findById(userDto.getRole_id())
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
        List<Role> roles = List.of(role);
        User user = mapper.toEntity(userDto);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public TokenDto login(LoginDto loginDto) {
        var user = userRepository.findByUsername(loginDto.getUsername());
        if(user.isEmpty())
            throw new UnauthenticatedException("Username or password incorrect");

        if(!passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword()))
            throw new UnauthenticatedException("Username or password incorrect");

        var token = jwtService.generateToken(user.get());
        return new TokenDto(token);
    }
}
