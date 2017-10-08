package com.blue33sun.recipe.model.recipe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:Step
 * Description:步骤
 * Author:lanjing
 * Date:2017/10/5 11:50
 */

public class Step implements Parcelable {
    private String img;
    private String step;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Step{" +
                "img='" + img + '\'' +
                ", step='" + step + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.img);
        dest.writeString(this.step);
    }

    public Step() {
    }

    protected Step(Parcel in) {
        this.img = in.readString();
        this.step = in.readString();
    }

    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel source) {
            return new Step(source);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
}
