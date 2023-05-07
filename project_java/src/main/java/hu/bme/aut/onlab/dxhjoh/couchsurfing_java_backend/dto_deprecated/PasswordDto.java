package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.dto_deprecated;

import lombok.Data;

@Data
public class PasswordDto {

    private String oldPassword;
    private String newPassword;
}
