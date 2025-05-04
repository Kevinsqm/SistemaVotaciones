package api.sistemaVotaciones.users.controller;

import api.sistemaVotaciones.security.TokenDto;
import api.sistemaVotaciones.users.DTOs.LoginDto;
import api.sistemaVotaciones.users.DTOs.UserRequesDto;
import api.sistemaVotaciones.users.service.interfaces.IUserService;
import jakarta.validation.Valid;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequesDto userDto){
        userService.save(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }
}
