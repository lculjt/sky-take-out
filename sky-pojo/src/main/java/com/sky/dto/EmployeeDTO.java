package com.sky.dto;

import com.sky.annotation.Gender;
import com.sky.annotation.IdCard;
import com.sky.validator.group.employee.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class EmployeeDTO implements Serializable {
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Long id;

    @NotEmpty(message = "用户名不能为空")
    @Size(min = 6, max = 18, message = "用户名长度必须在6-18个字符之间")
    private String username;

    @NotEmpty(message = "姓名不能为空")
    private String name;

    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Gender(message = "性别只能是男或女")
    @NotEmpty(message = "性别不能为空")
    private String sex;

    @NotEmpty(message = "身份证号不能为空")
    @IdCard(message = "身份证号码格式错误或校验码不匹配")
    private String idNumber;

}
