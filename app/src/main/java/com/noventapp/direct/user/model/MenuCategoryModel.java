package com.noventapp.direct.user.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class MenuCategoryModel extends ExpandableGroup<MenuSubCategoryModel> {
    private String name;
    private List<MenuSubCategoryModel> subCategoryList;

    public MenuCategoryModel(String name, List<MenuSubCategoryModel> subCategoryList) {
        super(name, subCategoryList);
        this.subCategoryList = subCategoryList;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuSubCategoryModel> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<MenuSubCategoryModel> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }


}


