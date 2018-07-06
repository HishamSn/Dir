package com.noventapp.direct.user.ui.details.menu;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.MenuCategoryModel;
import com.noventapp.direct.user.model.MenuSubCategoryModel;
import com.noventapp.direct.user.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends BaseFragment {


    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    Unbinder unbinder;
    List<MenuCategoryModel> categoryList = new ArrayList<>();
    List<MenuCategoryModel> baseCategory = new ArrayList<>();
    @BindView(R.id.et_search)
    EditText etSearch;
    MenuAdapter adapter;
    private int i;


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        unbinder = ButterKnife.bind(this, view);
        setListData();
        setRecyclerView();
        setSearchFunctionality();
        return view;
    }

    private void setSearchFunctionality() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterData(s.toString());
            }
        });
    }


    public void filterData(String query) {

        query = query.toLowerCase();
        baseCategory.clear();

        if (query.isEmpty()) {
            baseCategory.addAll(categoryList);
        } else {
            for (MenuCategoryModel categoryModel : categoryList) {
                List<MenuSubCategoryModel> subCategoryList = categoryModel.getSubCategoryList();
                ArrayList<MenuSubCategoryModel> tempSubCategoryList = new ArrayList<>();

                for (MenuSubCategoryModel subCategoryModel : subCategoryList) {
                    if (subCategoryModel.getName().toLowerCase().contains(query)) {
                        tempSubCategoryList.add(subCategoryModel);
                    }
                }


                if (tempSubCategoryList.size() > 0) {
                    MenuCategoryModel newMenuCategory = new MenuCategoryModel(categoryModel.getTitle()
                            , tempSubCategoryList);
                    baseCategory.add(newMenuCategory);
                }
            }
        }
        adapter = new MenuAdapter(baseCategory);
        // need "if,else" here before sit the recycler adapter again to expand and collapse the groups
        if (query.isEmpty()) {
            for (i = 0; i < adapter.getGroups().size(); i++) {
                if (adapter.isGroupExpanded(i)) {
                    adapter.toggleGroup(i);
                }
            }
        } else {
            for (i = adapter.getGroups().size() - 1; i >= 0; i--) {
                adapter.toggleGroup(i);
            }
        }
        rvMenu.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    private void setRecyclerView() {
        rvMenu.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new MenuAdapter(categoryList);
        rvMenu.setAdapter(adapter);
    }

    private void setListData() {
        ArrayList<MenuSubCategoryModel> shawarma = new ArrayList<>();
        shawarma.add(new MenuSubCategoryModel("Shawarma"));
        shawarma.add(new MenuSubCategoryModel("awarma"));
        shawarma.add(new MenuSubCategoryModel("warma"));


        ArrayList<MenuSubCategoryModel> burger = new ArrayList<>();
        burger.add(new MenuSubCategoryModel("burger"));
        burger.add(new MenuSubCategoryModel("mall_burger"));
        burger.add(new MenuSubCategoryModel("mid_burger"));
        burger.add(new MenuSubCategoryModel("chicken_burger"));

        categoryList.add(new MenuCategoryModel("top selling", shawarma));
        categoryList.add(new MenuCategoryModel("most popular", burger));
        baseCategory.addAll(categoryList);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
