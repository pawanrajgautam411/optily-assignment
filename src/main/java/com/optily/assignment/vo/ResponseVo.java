package com.optily.assignment.vo;

import java.util.List;

/**
 *
 */
public class ResponseVo {
    private boolean success;
    private Object data;
    private List<String> errors;

    /**
     *
     */
    public ResponseVo(){
        this.success = true;
    }

    /**
     * @param data
     */
    public ResponseVo(Object data) {
        this.success = true;
        this.data = data;
    }

    /**
     * @param errors
     */
    public ResponseVo(List<String> errors) {
        this.success = false;
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
