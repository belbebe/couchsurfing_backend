package hu.bme.aut.onlab.dxhjoh.couchsurfing_java_backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CustomEmailValidator implements ConstraintValidator<EmailValidation, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Pattern.compile("^\\S+@\\S+\\.\\S+$").matcher(s).matches();
    }
}
