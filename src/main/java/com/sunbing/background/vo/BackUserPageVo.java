package com.sunbing.background.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sunbing
 * @version 1.0
 * @since 2020/12/14 10:59
 */
@Data
public class BackUserPageVo {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sexDesc;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

}
