package com.sunbing.background.param;

import lombok.Data;

/**
 * @author sunbing
 * @version 1.0
 * @since 2020/12/22 10:36
 */
@Data
public class BackUserSaveParam {
    private Integer id;
    private String userName;
    private String password;
    private String confirmPassword;
    private String name;
    private Integer sex;
    private String mobile;
    private String email;
}
