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

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
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
    List<MenuSubCategoryModel> subCategoryList = new ArrayList<>();
    List<ParentObject> parentObjects = new ArrayList<>();
    @BindView(R.id.et_search)
    EditText etSearch;
    MenuAdapter adapter;


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
        setReyclerView();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    for (int i = 0; i < adapter.getGroups().size(); i++) {
                        if (adapter.isGroupExpanded(i)) {
                            adapter.toggleGroup(i);
                        }
                    }

                } else {
                    for (int i = adapter.getGroups().size() - 1; i >= 0; i--) {
                        adapter.toggleGroup(i);
                    }
                }

                adapter.filterData(s.toString());


//                ArrayList<MenuCategoryModel> nameTest = new ArrayList<>();
//                for (MenuCategoryModel data : categoryList) {
//                    if (data.getSubCategoryList().get(0).getName().toLowerCase().startsWith(s.toString().toLowerCase())) {
//                        nameTest.add(data);
//                    }
//                }
//
//                adapter.toggleGroup(0);
//                adapter.notifyDataSetChanged();
                // rvMenu.setAdapter(new MenuAdapter(nameTest));

            }
        });
        return view;
    }


    private void setReyclerView() {
        rvMenu.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new MenuAdapter(categoryList);
        rvMenu.setAdapter(adapter);
    }

    private void setListData() {
        ArrayList<MenuSubCategoryModel> iphones = new ArrayList<>();
        iphones.add(new MenuSubCategoryModel("Shawarma"));
        iphones.add(new MenuSubCategoryModel("awarma"));
        iphones.add(new MenuSubCategoryModel("warma"));


        ArrayList<MenuSubCategoryModel> nexus = new ArrayList<>();
        nexus.add(new MenuSubCategoryModel("burger"));
        nexus.add(new MenuSubCategoryModel("small_burger"));
        nexus.add(new MenuSubCategoryModel("mid_burger"));
        nexus.add(new MenuSubCategoryModel("chicken_burger"));


//        MenuCategoryModel md1 = new MenuCategoryModel("top selling", iphones);
//        md1.setSubCategoryList(iphones);
//        categoryList.add(md1);
        categoryList.add(new MenuCategoryModel("top selling", iphones));
        categoryList.add(new MenuCategoryModel("tasneem", nexus));

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
