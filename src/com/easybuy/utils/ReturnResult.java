package com.easybuy.utils;

import java.io.Serializable;

public class ReturnResult  implements Serializable {
    private int status;//记录服务器处理的状态
    private Object data;//记录服务器返回的数据
    private String message = "操作成功";//服务器操作给前台的一些信息

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 返回成功的状态
     * @param object
     * @return
     */
    public ReturnResult returnSuccess(Object object){
        this.status = Constants.ReturnResult.SUCCESS;
        this.data = object;
        return this;
    }

    /**
     * 返回默认的成功的状态
     * @return
     */
    public ReturnResult returnSuccess(){
        this.status = Constants.ReturnResult.SUCCESS;
        return this;
    }

    /**
     * 返回失败的状态
     * @param message
     * @return
     */
    public ReturnResult returnFail(String message){
        this.status = Constants.ReturnResult.FAIL;
        this.message = message;
        return this;
    }

    /**
     * 构造函数
     * @param message
     * @param status
     * @param data
     */
    public ReturnResult(String message,int status,Object data){
        this.message = message;
        this.status = status;
        this.data = data;
    }

    /**
     * 构造函数
     * @param data
     */
    public ReturnResult(Object data){
        this.status = Constants.ReturnResult.SUCCESS;
        this.data = data;
    }

    /**
     * 空参构造
     */
    public ReturnResult (){

    }

}
