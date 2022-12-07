package hu.bme.aut.onlab.couchsurfing_backend.controller

import hu.bme.aut.onlab.couchsurfing_backend.dto.PasswordDto
import hu.bme.aut.onlab.couchsurfing_backend.dto.UserDto
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Slf4j
@RestController
@RequestMapping("/api/user")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
class UserController(private val userService: UserService) {

    // saját felhasználó megtekintése
    @GetMapping
    fun getMyUser(): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getMe())
    }

    // saját felhasználó módosítása
    @PutMapping
    fun updateMyUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.updateMe(userDto))
    }

    // saját felhasználó jelszavának módosítása
    @PutMapping("/password")
    fun updateMyPassword(@RequestBody passwordDto: PasswordDto): ResponseEntity<Void> {
        userService.password(passwordDto)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    // saját felhasználó törlése
    @DeleteMapping
    fun deleteMyUser(): ResponseEntity<Void> {
        userService.deleteMe()
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    // felhasználó adatainak lekérése a felhasználó id-ja alapján
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.getUser(id))
    }
}