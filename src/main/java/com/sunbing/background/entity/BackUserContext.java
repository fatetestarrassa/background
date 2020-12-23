package com.sunbing.background.entity;

/**
 * 后台用户上下文,利用ThreadLocal存储登录用户
 *
 * @author sunbing
 * @version 1.0
 * @since 2020/12/9 11:31
 */
public class BackUserContext {
    private static final ThreadLocal<BackUser> CONTEXT = new ThreadLocal<>();

    private BackUserContext(){

    }

    /**
     * 存放用户
     * @param backUser 用户
     */
    public static void set(BackUser backUser){
        CONTEXT.set(backUser);
    }

    /**
     * 获取用户
     * @return 用户对象
     */
    public static BackUser get(){
        return CONTEXT.get();
    }
}
