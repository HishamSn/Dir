package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.databinding.RowSearchCategoryBinding;
import com.noventapp.direct.user.model.PrimeFilterCategory;
import com.noventapp.direct.user.ui.base.BaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CategorySearchAdapter extends BaseAdapter<CategorySearchAdapter.ViewHolder> {


    private int widthScreenRV;
    private int heightScreenRV;
    private int topMarginRV = 5;
    private int rightMarginRV = 1;
    private int leftMarginRV = 1;

    private static final int ROW_SEARCH_CATEGORY = R.layout.row_search_category;
    private Context context;

    private List<PrimeFilterCategory> primeFilterCategoryList;
    private boolean hasProgress = true;
    private boolean isBottomSheetSearchActive;

    public CategorySearchAdapter(List<PrimeFilterCategory> primeFilterCategoryList, boolean isBottomSheetSearchActive) {
        this.primeFilterCategoryList = primeFilterCategoryList;
        this.isBottomSheetSearchActive = isBottomSheetSearchActive;
        widthScreenRV = Resources.getSystem().getDisplayMetrics().widthPixels;
    }


    @Override
    public int getItemViewType(int position) {
        return ROW_SEARCH_CATEGORY;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
//        recyclerView.post(new Runnable() {
//            @Override
//            public void run() {
////                recyclerView.getMeasuredWidth();
//            }
//        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ConstraintLayout.LayoutParams layoutParams;
        if (!isBottomSheetSearchActive) {
            int width = widthScreenRV / 3;
            layoutParams = new ConstraintLayout.LayoutParams(
                    width + (width / 4),
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            if (position == getItemCount() - 1) {
                layoutParams.setMargins(leftMarginRV, topMarginRV, 0, 0);
            } else {
                layoutParams.setMargins(0, topMarginRV, rightMarginRV, 0);
            }
        } else {
            layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
            );
            if (position % 2 == 1) {
                layoutParams.setMargins(leftMarginRV, topMarginRV, 0, 0);
            } else {
                layoutParams.setMargins(0, topMarginRV, rightMarginRV, 0);
            }
        }


        holder.itemView.setLayoutParams(layoutParams);


        try {
            loadImage(holder, primeFilterCategoryList.get(position).getFilterImage());
        } catch (Exception e) {

        }
        holder.tvCategoryName.setText(primeFilterCategoryList.get(position).getBaseName());
        holder.itemView.setOnClickListener(v -> {
        });
    }


    private void loadImage(CategorySearchAdapter.ViewHolder holder, String url) throws Exception {
        Picasso.with(holder.ivCategoryImage.getContext()).load(url).into(holder.ivCategoryImage);
    }


    @Override
    public int getItemCount() {
        return primeFilterCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowSearchCategoryBinding binding;
        AppCompatImageView ivCategoryImage;
        AppCompatTextView tvCategoryName;

        public ViewHolder(RowSearchCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public ViewHolder(View view) {
            super(view);
            ivCategoryImage = view.findViewById(R.id.iv_popular_categories);
            tvCategoryName = view.findViewById(R.id.tv_name);
        }
    }
}
