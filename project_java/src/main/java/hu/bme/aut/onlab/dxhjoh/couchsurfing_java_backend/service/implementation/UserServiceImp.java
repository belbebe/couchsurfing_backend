package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.implementation;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto_deprecated.PasswordDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto_deprecated.UserDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception.CouchsurfingRuntimeException;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper.UserMapper;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.UserRepository;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.PasswordRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.UserRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.UserResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.UserService;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.util.ContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.util.ContextUtil.ANONYMOUS_USER;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse save(UserRequest userRequest) {
        log.trace("UserService : save, username=[{}]", userRequest.getUsername());
        validateUsername(userRequest.getUsername());
        validateUsernameDoesNotExist(userRequest.getUsername());
        User createdUser = userRepository.save(userMapper.toEntity(userRequest));
        return userMapper.toResponse(createdUser);
    }

    private void validateUsername(String username) {
        validateUsernameAlphanumeric(username);
        validateUsernameLength(username);
        validateUsernameInvalid(username);
    }

    /**
     * Ellenőrzi, hogy a megadott felhasználónév csak alfanumerikus karaktereket tartalmaz-e
     *
     * @param username adott felhasználónév
     */
    private void validateUsernameAlphanumeric(String username) {
        if (!StringUtils.isAlphanumeric(username)) {
            throw new CouchsurfingRuntimeException("error.user.username.alphanumeric");
        }
    }

    /**
     * Ellenőrzi, hogy a megadott felhasználónév megfelelően hosszú-e
     *
     * @param username adott felhasználónév
     */
    private void validateUsernameLength(String username) {
        if (username.length() < 3) {
            throw new CouchsurfingRuntimeException("error.user.username.short");
        }
    }

    /**
     * Ellenőrzi, hogy valid-e a megadott felhasználónév
     *
     * @param username adott felhasználónév
     */
    private void validateUsernameInvalid(String username) {
        if (ANONYMOUS_USER.equals(username)) {
            throw new CouchsurfingRuntimeException("error.user.username.invalid");
        }
    }

    private void validateUsernameDoesNotExist(String username) {
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new CouchsurfingRuntimeException("error.user.username.taken");
        }
    }

    @Override
    public UserResponse get(int id) {
        log.trace("UserService : get, id=[{}]", id);
        return userMapper.toResponse(findById(id));
    }

    @Override
    public User findById(int id) {
        log.trace("UserService : findById, id=[{}]", id);
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        log.trace("UserService : getAll");
        return userRepository.findAll(pageable).map(userMapper::toResponse);
    }

    @Override
    public User findByUsername(String username) {
        log.trace("UserService : updateMe, username=[{}]", username);
        return userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public UserResponse getMe() {
        log.trace("UserService : getMe");
        return userMapper.toResponse(getCurrentUserEntity());
    }

    @Override
    public User getCurrentUserEntity() {
        log.trace("UserService : getCurrentUserEntity");
        return findByUsername(ContextUtil.getCurrentUsername());
    }

    @Override
    public UserResponse updateMe(UserRequest userReq) {
        log.trace("UserService : updateMe, userDto=[{}]", userReq);
        User updatedUser = userMapper.update(getCurrentUserEntity(), userReq);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public UserResponse update(int id, UserRequest userReq) {
        log.trace("UserService : update, userDto=[{}]", userReq);
        User updatedUser = userMapper.update(findById(id), userReq);
        return userMapper.toResponse(updatedUser);
    }

    @Override
    public void delete(int id) {
        log.trace("UserService : delete, id=[{}]", id);
        userRepository.delete(findById(id));
    }

    @Override
    public void deleteMe() {
        log.trace("UserService : delete");
        userRepository.delete(getCurrentUserEntity());
    }

    @Override
    public void password(PasswordRequest passwordReq) {
        log.trace("UserService : password, passwordDto=[{}]", passwordReq);
        //validateOldPassword(passwordReq.getOldPassword());
        //setNewPassword(pass.getNewPassword());
    }

    /*
    private void validateOldPassword(String password) {
        if (!passwordEncoder.matches(password, getCurrentUserEntity().getPassword())) {
            throw new CouchsurfingRuntimeException("error.password.invalid");
        }
    }

    private void setNewPassword(String password) {
        getCurrentUserEntity().setPassword(passwordEncoder.encode(password));
    }
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace("UserService : loadUserByUsername, username=[{}]", username);

        User user = findByUsername(username);
        validateUserExists(user);

        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword());
        return null;
    }

    private void validateUserExists(User user) {
        if (user == null) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
    }
}
