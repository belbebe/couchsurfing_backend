package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper.UserMapper;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.UserRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.LoginEmailRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.UserRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.LoginResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.UserResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    final AuthenticationManager authenticationManager;
    final UserRepository userRepository;
    final PasswordEncoder encoder;
    final JwtUtil jwtUtils;
    final UserMapper userMapper;

    public LoginResponse login(LoginEmailRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUsername(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        String jwt = jwtUtils.generateJwtToken(user.getUsername(), roles);
        return (new LoginResponse(jwt, jwtUtils.getJwtExpiration(), "Bearer", roles));
    }

    public UserResponse register(UserRequest registrationRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByUsernameIgnoreCase(registrationRequest.getUsername()))) {
            throw new RuntimeException("Error: Username is already taken!");
        }
        if (Boolean.TRUE.equals(userRepository.existsByEmail(registrationRequest.getEmail()))) {
            throw new RuntimeException("Error: Email is already in use!");
        }
        User user = userMapper.toEntity(registrationRequest);
        user.setPassword(encoder.encode(registrationRequest.getPassword()));
        return userMapper.toResponse(userRepository.save(user));
        //return login(new LoginEmailRequest(registrationRequest.getEmail(), registrationRequest.getPassword()));
    }
}
