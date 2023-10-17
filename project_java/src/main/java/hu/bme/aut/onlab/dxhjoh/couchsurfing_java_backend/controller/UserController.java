package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.controller;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.PasswordRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.UserRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.UserResponse;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // saját felhasználó megtekintése
    @GetMapping
    //@Operation(summary = "Saját felhasználó megtekintése")
    public ResponseEntity<UserResponse> getMyUser() {
        log.trace("UserController : getMyUser");
        return ResponseEntity.ok(userService.getMe());
    }

    // saját felhasználó módosítása
    @PutMapping
    //@Operation(summary = "Saját felhasználó módosítása")
    public ResponseEntity<UserResponse> updateMyUser(@RequestBody UserRequest userReq) {
        log.trace("UserController : updateMyUser, userDto=[{}]", userReq);
        return ResponseEntity.ok(userService.updateMe(userReq));
    }

    // saját felhasználó jelszavának módosítása
    @PutMapping("/password")
    //@Operation(summary = "Saját felhasználó jelszavának módosítása")
    public ResponseEntity<Void> updateMyPassword(@RequestBody PasswordRequest passwordReq) {
        log.trace("UserController : updateMyPassword, passwordDto=[{}]", passwordReq);
        userService.password(passwordReq);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // saját felhasználó törlése
    @DeleteMapping
    //@Operation(summary = "Saját felhasználó törlése")
    public ResponseEntity<Void>  deleteMyUser(){
        userService.deleteMe();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    // felhasználó adatainak lekérése a felhasználó id-ja alapján
    @GetMapping("/{id}")
    //@Operation(summary = "Felhasználó adatainak lekérése a felhasználó id-ja alapján")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        log.trace("UserController : getUser, id=[{}]", id);
        return ResponseEntity.ok(userService.get(id));
    }
}
