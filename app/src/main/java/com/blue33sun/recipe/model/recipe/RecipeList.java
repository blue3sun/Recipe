package com.blue33sun.recipe.model.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ClassName:RecipeList
 * Description:食谱列表
 * Author:lanjing
 * Date:2017/10/5 12:00
 */

public class RecipeList implements Parcelable {
    private List<Recipe> data;
    private String totalNum;
    private String pn;
    private String rn;

    @Override
    public String toString() {
        return "RecipeList{" +
                "data=" + data +
                ", totalNum='" + totalNum + '\'' +
                ", pn='" + pn + '\'' +
                ", rn='" + rn + '\'' +
                '}';
    }

    public List<Recipe> getData() {
        return data;
    }

    public void setData(List<Recipe> data) {
        this.data = data;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
        dest.writeString(this.totalNum);
        dest.writeString(this.pn);
        dest.writeString(this.rn);
    }

    public RecipeList() {
    }

    protected RecipeList(Parcel in) {
        this.data = in.createTypedArrayList(Recipe.CREATOR);
        this.totalNum = in.readString();
        this.pn = in.readString();
        this.rn = in.readString();
    }

    public static final Parcelable.Creator<RecipeList> CREATOR = new Parcelable.Creator<RecipeList>() {
        @Override
        public RecipeList createFromParcel(Parcel source) {
            return new RecipeList(source);
        }

        @Override
        public RecipeList[] newArray(int size) {
            return new RecipeList[size];
        }
    };
}
