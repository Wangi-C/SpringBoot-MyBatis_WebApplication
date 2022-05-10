package org.mybatis.myservice.validation;

import org.mybatis.myservice.model.MemberVO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class InsertMemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return MemberVO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberVO member = (MemberVO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "job", "required");
    }
}
