package com.sunbing.background.entity;

import com.sunbing.background.enums.StatusCodeEnum;

/**
 * 响应结果实体
 */
public class ResponseResult<T> {
    /**
     * 响应编码。0-正常，小于0-系统级错误，大于0-业务级错误
     */
    private int returncode = 0;
    /**
     * 响应消息。code非0时，message非空
     */
    private String message = "OK";
    /**
     * 响应结果
     */
    private T result;

    public static <T> ResponseResult<T> Fail(StatusCodeEnum statusCode){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setReturncode(statusCode.getCode());
        result.setMessage(statusCode.getMessage());
        return result;
    }

    public static <T> ResponseResult<T> Fail(String failMsg){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setMessage(failMsg);
        result.setReturncode(-1);
        return result;
    }

    public static <T> ResponseResult<T> Fail(int code, String failMsg){
        ResponseResult<T> rsres = new ResponseResult<T>();
        rsres.setMessage(failMsg);
        rsres.setReturncode(code);
        return rsres;
    }

	public static <T> ResponseResult<T> Success(T result){
		ResponseResult<T> responseResult = new ResponseResult<T>();
        responseResult.setResult(result);
		return responseResult;
	}
	
	public boolean success(){
    	return returncode == 0;
	}

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
