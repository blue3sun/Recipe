package com.blue33sun.recipe.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:BaseModel
 * Description:Http请求数据的基本Model
 * Author:lanjing
 * Date:2017/10/4 22:07
 */

public class BaseModel implements Parcelable {
    private String resultcode;//返回码
    private String reason;//返回说明
    private int error_code;//返回错误码

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public BaseModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resultcode);
        dest.writeString(this.reason);
        dest.writeInt(this.error_code);
    }

    protected BaseModel(Parcel in) {
        this.resultcode = in.readString();
        this.reason = in.readString();
        this.error_code = in.readInt();
    }

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel source) {
            return new BaseModel(source);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };
}
