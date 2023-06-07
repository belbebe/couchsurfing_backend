package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.service.declaration;

import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto_deprecated.PasswordDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto_deprecated.UserDto;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.model.User;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.PasswordRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.request.UserRequest;
import hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * Új felhasználó mentése
     *
     * @param userReq az új felhasználó adatai
     * @return a mentett felhasználó
     */
    UserResponse createUser(UserRequest userReq);

    /**
     * Felhasználó lekérése id alapján
     *
     * @param id felhasználó azonosítója
     * @return a lekért felhasználó adatai (UserDto típussal)
     */
    UserResponse get(int id);

    /**
     * Felhasználó keresése id alapján
     *
     * @param id felhasználó azonosítója
     * @return a talált felhasználó entitás (User entitás objektum)
     */
    User findById(int id);

    /**
     * Egy oldalnyi felhasználó lekérése
     *
     * @return egy oldalnyi felhasználó listája
     */
    Page<UserResponse> getAll(Pageable pageable);

    /**
     * Felhasználó entitás keresése név alapján
     *
     * @param username felhasználónév
     * @return a talált felhasználó entitás
     */
    User findByUsername(String username);

    /**
     * Aktuálisan bejelentkezett (azaz a saját) felhasználó adatainak lekérése
     *
     * @return talált felhasználó adatai
     */
    UserResponse getMe();

    /**
     * Aktuálisan bejelentkezett felhasználó entitás
     *
     * @return felhasználó entitás
     */
    User getCurrentUserEntity();

    /**
     * Aktuálisan bejelentkezett felhasználó adatainak módosítása
     *
     * @param userReq új adatok
     * @return módosított felhasználó adatai
     */
    UserResponse updateMe(UserRequest userReq);

    /**
     * Felhasználó adatainak módosítása id alapján
     *
     * @param id felhasználó azonosítója
     * @param userReq módosított adatok
     * @return a módosított felhasználó
     */
    UserResponse update(int id, UserRequest userReq);

    /**
     * Felhasználó törlése id alapján
     *
     * @param id felhasználó azonosítója
     */
    void delete(int id);

    /**
     * Aktuálisan bejelentkezett felhasználó törlése
     *
     */
    void deleteMe();

    /**
     * Aktuálisan bejelentkezett felhasználó jelszavának módosítása
     *
     * @param passwordReq régi és új jelszó
     */
    void password(PasswordRequest passwordReq);
}
