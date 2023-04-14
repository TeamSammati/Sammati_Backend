package site.sammati.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.sammati.entity.User;
import site.sammati.repository.UserRepository;
import site.sammati.service.AuthenticationService;
import site.sammati.service.UserSevice;
import site.sammati.util.AuthenticationRequest;
import site.sammati.util.AuthenticationResponse;
import site.sammati.util.RegisterRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return  ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ){
        User patient=userRepository.findByUserName(request.getUserName()).get();
        AuthenticationResponse authenticationResponse=AuthenticationResponse.builder()
                .token(authenticationService.authenticate(request).getToken())
                .build();
        return ResponseEntity.ok(authenticationResponse);
    }

}
