package com.blue33sun.recipe.model.recipe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * ClassName:Recipe
 * Description:食谱
 * Author:lanjing
 * Date:2017/10/5 11:54
 */

public class Recipe implements Parcelable {
    public String id;
    public String title;
    public String tags;
    public String imtro;
    public String ingredients;//eg："鲈鱼,1个;柠檬,2个;红椒,6个"
    public String burden;//eg:"大蒜头,适量;香菜,适量;盐,适量;生姜,适量"
    public List<String> albums;
    public List<Step> steps;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImtro() {
        return imtro;
    }

    public void setImtro(String imtro) {
        this.imtro = imtro;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getBurden() {
        return burden;
    }

    public void setBurden(String burden) {
        this.burden = burden;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", imtro='" + imtro + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", burden='" + burden + '\'' +
                ", albums=" + albums +
                ", steps=" + steps +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.tags);
        dest.writeString(this.imtro);
        dest.writeString(this.ingredients);
        dest.writeString(this.burden);
        dest.writeStringList(this.albums);
        dest.writeTypedList(this.steps);
    }

    public Recipe() {
    }

    protected Recipe(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.tags = in.readString();
        this.imtro = in.readString();
        this.ingredients = in.readString();
        this.burden = in.readString();
        this.albums = in.createStringArrayList();
        this.steps = in.createTypedArrayList(Step.CREATOR);
    }

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
