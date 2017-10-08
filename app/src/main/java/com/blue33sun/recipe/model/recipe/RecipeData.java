package com.blue33sun.recipe.model.recipe;

import android.os.Parcel;

import com.blue33sun.recipe.model.BaseModel;

/**
 * ClassName:RecipeData
 * Description:封装的食谱列表对应的网络请求对象
 * Author:lanjing
 * Date:2017/10/5 14:30
 */

public class RecipeData extends BaseModel{
    private RecipeList result;

    public RecipeList getResult() {
        return result;
    }

    public void setResult(RecipeList result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "RecipeData{" +
                "result=" + result +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.result, flags);
    }

    public RecipeData() {
    }

    protected RecipeData(Parcel in) {
        super(in);
        this.result = in.readParcelable(RecipeList.class.getClassLoader());
    }

    public static final Creator<RecipeData> CREATOR = new Creator<RecipeData>() {
        @Override
        public RecipeData createFromParcel(Parcel source) {
            return new RecipeData(source);
        }

        @Override
        public RecipeData[] newArray(int size) {
            return new RecipeData[size];
        }
    };
}
