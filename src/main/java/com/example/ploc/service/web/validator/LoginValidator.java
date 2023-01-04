package com.example.ploc.service.web.validator;

import com.example.ploc.dto.login.LoginFormDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginFormDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginFormDTO login = (LoginFormDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "university", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject", "required");
    }
}
