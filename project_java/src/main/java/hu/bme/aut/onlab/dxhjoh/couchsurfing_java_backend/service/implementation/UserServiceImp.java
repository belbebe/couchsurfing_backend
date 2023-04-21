package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.implementation;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto.PasswordDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto.UserDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.exception.CouchsurfingRuntimeException;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.mapper.UserMapper;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.repository.UserRepository;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        log.trace("UserService : save, username=[{}]", userDto.getUsername());
        validateUsername(userDto.getUsername());
        validateUsernameDoesNotExist(userDto.getUsername());
        User createdUser = userRepository.save(userMapper.toEntity(userDto));
        return userMapper.toDto(createdUser);
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
    public UserDto get(int id) {
        log.trace("UserService : get, id=[{}]", id);
        return userMapper.toDto(findById(id));
    }

    @Override
    public User findById(int id) {
        log.trace("UserService : findById, id=[{}]", id);
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        log.trace("UserService : getAll");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public User findByUsername(String username) {
        log.trace("UserService : updateMe, username=[{}]", username);
        return userRepository.findByUsername(username)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public UserDto getMe() {
        log.trace("UserService : getMe");
        return userMapper.toDto(getCurrentUserEntity());
    }

    @Override
    public User getCurrentUserEntity() {
        log.trace("UserService : getCurrentUserEntity");
        return findByUsername(ContextUtil.getCurrentUsername());
    }

    @Override
    public UserDto updateMe(UserDto userDto) {
        log.trace("UserService : updateMe, userDto=[{}]", userDto);
        User updatedUser = userMapper.update(getCurrentUserEntity(), userDto);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public UserDto update(int id, UserDto userDto) {
        log.trace("UserService : update, userDto=[{}]", userDto);
        User updatedUser = userMapper.update(findById(id), userDto);
        return userMapper.toDto(updatedUser);
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
    public void password(PasswordDto passwordDto) {
        log.trace("UserService : password, passwordDto=[{}]", passwordDto);
        validateOldPassword(passwordDto.getOldPassword());
        setNewPassword(passwordDto.getNewPassword());
    }

    private void validateOldPassword(String password) {
        if (!passwordEncoder.matches(password, getCurrentUserEntity().getPassword())) {
            throw new CouchsurfingRuntimeException("error.password.invalid");
        }
    }

    private void setNewPassword(String password) {
        getCurrentUserEntity().setPassword(passwordEncoder.encode(password));
    }

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
