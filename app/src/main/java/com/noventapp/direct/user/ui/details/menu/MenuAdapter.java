package com.noventapp.direct.user.ui.details.menu;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.MenuCategoryModel;
import com.noventapp.direct.user.model.MenuSubCategoryModel;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

public class MenuAdapter extends ExpandableRecyclerViewAdapter<MenuAdapter.MainCategoryViewHolder,
        MenuAdapter.SubCategoryViewHolder>  {


    public MenuAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public MainCategoryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View mainCategory = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu_category, parent, false);
        return new MainCategoryViewHolder(mainCategory);
    }

    @Override
    public SubCategoryViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View subCategory = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_menu, parent, false);
        return new SubCategoryViewHolder(subCategory);
    }

    @Override
    public void onBindChildViewHolder(SubCategoryViewHolder holder, int flatPosition
            , ExpandableGroup group, int childIndex) {
        final MenuSubCategoryModel menuSubCategoryModel = ((MenuCategoryModel) group)
                .getItems().get(childIndex);
        holder.bind(menuSubCategoryModel);

    }

    @Override
    public void onBindGroupViewHolder(MainCategoryViewHolder holder, int flatPosition
            , ExpandableGroup group) {
        holder.setGroupName(group);
    }

    class MainCategoryViewHolder extends GroupViewHolder {
        AppCompatImageView iv_down_arrow;
        private AppCompatTextView tv_name;

        public MainCategoryViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_down_arrow = itemView.findViewById(R.id.iv_down_arrow);
        }

        @Override
        public void expand() {
            iv_down_arrow.setBackground(iv_down_arrow.getContext().getResources()
                    .getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp));
            Log.i("Adapter", "expand");
        }

        @Override
        public void collapse() {
            Log.i("Adapter", "collapse");
            iv_down_arrow.setBackground(iv_down_arrow.getContext().getResources()
                    .getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp));

        }

        public void setGroupName(ExpandableGroup group) {
            tv_name.setText(group.getTitle());
        }

    }

    class SubCategoryViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {
        AppCompatTextView tv_meal_name, tv_meal_price;
        AppCompatImageView iv_meal_image;

        public SubCategoryViewHolder(View itemView) {
            super(itemView);
            tv_meal_name = itemView.findViewById(R.id.tv_meal);
            tv_meal_price = itemView.findViewById(R.id.tv_meal_price);
            iv_meal_image = itemView.findViewById(R.id.iv_meal);


        }


        public void bind(MenuSubCategoryModel menuSubCategory) {
            tv_meal_name.setText(menuSubCategory.getName());

        }

    }
}



