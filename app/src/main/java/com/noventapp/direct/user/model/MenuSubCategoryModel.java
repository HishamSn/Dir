package com.noventapp.direct.user.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuSubCategoryModel implements Parcelable {
    public static final Creator<MenuSubCategoryModel> CREATOR = new Creator<MenuSubCategoryModel>() {
        @Override
        public MenuSubCategoryModel createFromParcel(Parcel in) {
            return new MenuSubCategoryModel(in);
        }

        @Override
        public MenuSubCategoryModel[] newArray(int size) {
            return new MenuSubCategoryModel[size];
        }
    };
    private String name;

    public MenuSubCategoryModel(String name) {
        this.name = name;
    }

    protected MenuSubCategoryModel(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
