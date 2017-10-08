package com.blue33sun.recipe.model.category;

import android.os.Parcel;

import com.blue33sun.recipe.model.BaseModel;

import java.util.List;

/**
 * ClassName:MenuCategories
 * Description:菜谱种类
 * Author:lanjing
 * Date:2017/10/4 17:27
 */

public class MenuCategories extends BaseModel {
    private List<MenuCategory> result;

    public List<MenuCategory> getResult() {
        return result;
    }

    public void setResult(List<MenuCategory> result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.result);
    }

    public MenuCategories() {
    }

    protected MenuCategories(Parcel in) {
        super(in);
        this.result = in.createTypedArrayList(MenuCategory.CREATOR);
    }

    public static final Creator<MenuCategories> CREATOR = new Creator<MenuCategories>() {
        @Override
        public MenuCategories createFromParcel(Parcel source) {
            return new MenuCategories(source);
        }

        @Override
        public MenuCategories[] newArray(int size) {
            return new MenuCategories[size];
        }
    };
}
