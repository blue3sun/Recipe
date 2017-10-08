package com.blue33sun.recipe.model.category;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ClassName:IMenuCategoryView
 * Description:菜谱种类
 * Author:lanjing
 * Date:2017/10/3 14:50
 */

public class MenuCategory implements Parcelable {
    private String name;//标签名称
    private String parentId;//分类ID
    private List<Category> list;//菜系集合

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MenuCategory{" +
                "name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", list=" + list +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.parentId);
        dest.writeTypedList(this.list);
    }

    public MenuCategory() {
    }

    protected MenuCategory(Parcel in) {
        this.name = in.readString();
        this.parentId = in.readString();
        this.list = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Parcelable.Creator<MenuCategory> CREATOR = new Parcelable.Creator<MenuCategory>() {
        @Override
        public MenuCategory createFromParcel(Parcel source) {
            return new MenuCategory(source);
        }

        @Override
        public MenuCategory[] newArray(int size) {
            return new MenuCategory[size];
        }
    };
}
