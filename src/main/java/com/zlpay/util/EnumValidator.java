package com.zlpay.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 枚举值校验注解
 *
 * @author: zetting
 * @date:2018/12/18
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = EnumValidatorClass.class)
public @interface EnumValidator {
    Class<?> value();

    String message() default "枚举值不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}