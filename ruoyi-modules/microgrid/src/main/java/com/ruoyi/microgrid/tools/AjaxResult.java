package com.ruoyi.microgrid.tools;

public class AjaxResult {

    private Object data;

    public AjaxResult() {
    }

    public AjaxResult(Object data) {
        this.data = data;
    }

    // Getters and setters
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static AjaxResult success(Object data) {
        return new AjaxResult(data);
    }
}
