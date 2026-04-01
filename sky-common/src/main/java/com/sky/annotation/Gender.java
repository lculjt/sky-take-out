package com.sky.annotation;

import com.sky.validator.GenderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {GenderValidator.class} // 自定义校验器去校验
)
public @interface Gender {
    String message() default "性别只能是男或女";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
