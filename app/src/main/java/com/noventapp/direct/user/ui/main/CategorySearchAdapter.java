package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
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

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_popular_categories;
    private static final int ROW_SEARCH_CATEGORY = R.layout.row_search_category;
    private Context context;
    private List<PrimeFilterCategory> primeFilterCategoryList;
    private boolean hasProgress = true;
    private boolean rowFlag;

    public CategorySearchAdapter(List<PrimeFilterCategory> primeFilterCategoryList, boolean rowFlag) {
        this.primeFilterCategoryList = primeFilterCategoryList;
        this.rowFlag = rowFlag;
    }

    public CategorySearchAdapter(List<PrimeFilterCategory> primeFilterCategoryList) {
        this.primeFilterCategoryList = primeFilterCategoryList;

    }


    @Override
    public int getItemViewType(int position) {
//        if (position == categoryModelList.size() && hasProgress) {
//            return ROW_REFRESH;
//        }

        return ROW_SEARCH_CATEGORY;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        RowSearchCategoryBinding binding = RowSearchCategoryBinding.inflate(
//                LayoutInflater.from(context),
//                parent,
//                false);
//        return new ViewHolder(binding);


        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new CategorySearchAdapter.ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            loadImage(holder, primeFilterCategoryList.get(position).getFilterImage());
        } catch (Exception e) {

        }
        holder.tvCategoryName.setText(primeFilterCategoryList.get(position).getBaseName());
        holder.itemView.setOnClickListener(v -> {
        });
//        holder.binding.executePendingBindings();
    }


    private void loadImage(CategorySearchAdapter.ViewHolder holder, String url) throws Exception {
        Picasso.with(holder.ivCategoryImage.getContext()).load(url).into(holder.ivCategoryImage);
    }


    @Override
    public int getItemCount() {
        // if we don't need loading in the first
//        return categoryModelList.size() > 0 ?
//                hasProgress ? categoryModelList.size() + 1 : categoryModelList.size() : 0;

//        return hasProgress ? categoryModelList.size() + 1 : categoryModelList.size();
        return primeFilterCategoryList.size();
//        return primeFilterCategoryList.size();
    }


    public void disableProgress() {
        hasProgress = false;
    }

    public void activeProgress() {
        hasProgress = true;
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
