package com.sxhxjy.roadmonitor.base;

import com.sxhxjy.roadmonitor.entity.Data;

/**
 * 2016/9/13
 *
 * @author Michael Zhao
 */
public class HttpResponse<T> {
    private int resultCode;
    private String resultMessage;

    private Data<T> data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }
}
