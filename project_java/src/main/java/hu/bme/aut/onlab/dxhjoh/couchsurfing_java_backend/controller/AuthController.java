package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.controller;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.LoginEmailRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.UserRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.LoginResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.UserResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginEmailRequest loginReq) {
        return ResponseEntity.ok(authService.login(loginReq));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userReq) {
        return ResponseEntity.ok(authService.register(userReq));
    }
}
