package com.sunbing.background.param;

import lombok.Data;

/**
 * @author sunbing
 * @version 1.0
 * @since 2020/12/14 11:00
 */
@Data
public class BackUserQueryParam {
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
     * 手机号
     */
    private String mobile;
    private Long current = 1L;
    private Long size = 10L;
}
