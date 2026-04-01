package com.sky.validator;

import com.sky.annotation.IdCard;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdCardValidator implements ConstraintValidator<IdCard, String> {
    // 18位身份证正则（基础格式校验）
    private static final String ID_CARD_REGEX = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";

    // 身份证最后一位校验码的加权因子
    private static final int[] WEIGHT_FACTOR = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 校验码对应值
    private static final char[] CHECK_CODE = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    @Override
    public void initialize(IdCard constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String idCard, ConstraintValidatorContext constraintValidatorContext) {
        // 1. 空值处理（如果需要非空，单独加@NotBlank注解）
        if (idCard == null || idCard.trim().isEmpty()) {
            return true; // 空值不校验，交由@NotBlank处理
        }

        String idCardStr = idCard.trim().toUpperCase();

        // 2. 基础格式校验
        if (!idCardStr.matches(ID_CARD_REGEX)) {
            return false;
        }

        // 3. 校验码验证（核心）
        return validateCheckCode(idCardStr);
    }

    /**
     * 验证身份证最后一位校验码
     */
    private boolean validateCheckCode(String idCard) {
        // 前17位数字
        char[] chars = idCard.substring(0, 17).toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum += (chars[i] - '0') * WEIGHT_FACTOR[i];
        }

        // 计算校验码
        int mod = sum % 11;
        char expectedCheckCode = CHECK_CODE[mod];
        char actualCheckCode = idCard.charAt(17);

        // 对比校验码
        return expectedCheckCode == actualCheckCode;
    }
}
