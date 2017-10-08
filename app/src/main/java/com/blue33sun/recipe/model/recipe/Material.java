package com.blue33sun.recipe.model.recipe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ClassName:Material
 * Description:菜谱中所需材料和用量
 * Author:lanjing
 * Date:2017/10/5 19:56
 */

public class Material implements Parcelable {
    private String name;
    private String amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.amount);
    }

    public Material() {
    }

    protected Material(Parcel in) {
        this.name = in.readString();
        this.amount = in.readString();
    }

    public static final Parcelable.Creator<Material> CREATOR = new Parcelable.Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel source) {
            return new Material(source);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };
}
