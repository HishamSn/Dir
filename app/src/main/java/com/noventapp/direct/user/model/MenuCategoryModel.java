package com.noventapp.direct.user.model;

import java.util.List;

public class MenuCategoryModel implements ParentListItem {
    private String name;
    private List<MenuSubCategoryModel> subCategoryList;

    public MenuCategoryModel(String name, List<MenuSubCategoryModel> subCategoryList) {
        this.name = name;
        this.subCategoryList = subCategoryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public List<?> getChildItemList() {
        return subCategoryList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}


