package com.sky.annotation;


import com.sky.validator.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义身份证号码校验注解
 */
@Documented
@Constraint(validatedBy = IdCardValidator.class) // 指定校验器
@Target({ElementType.FIELD, ElementType.PARAMETER}) // 作用于字段和参数
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
public @interface IdCard {

    // 默认错误提示
    String message() default "身份证号码格式不正确";

    // 分组校验
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}