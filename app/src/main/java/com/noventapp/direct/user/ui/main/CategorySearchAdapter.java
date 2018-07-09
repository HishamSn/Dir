package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.databinding.RowSearchCategoryBinding;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.List;


public class CategorySearchAdapter extends BaseAdapter<CategorySearchAdapter.ViewHolder> {

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_popular_categories;
    private static final int ROW_SEARCH_CATEGORY = R.layout.row_search_category;
    private Context context;
    private List<Object> categoryModelList;
    private boolean hasProgress = true;
    private boolean rowFlag;

    public CategorySearchAdapter(List<Object> categoryModelList, boolean rowFlag) {
        this.categoryModelList = categoryModelList;
        this.rowFlag = rowFlag;
    }

    public CategorySearchAdapter(boolean rowFlag) {

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
        RowSearchCategoryBinding binding = RowSearchCategoryBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false);
        return new ViewHolder(binding);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, PlacesActivity.class);
//            intent.putExtra("CATEGORY_ID",
//                    categoryModelList.get(position).getCategoryId());
//            intent.putExtra("CATEGORY_NAME",
//                    categoryModelList.get(position).getBaseCategoryName());
//            context.startActivity(intent);
        });
        // holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        // if we don't need loading in the first
//        return categoryModelList.size() > 0 ?
//                hasProgress ? categoryModelList.size() + 1 : categoryModelList.size() : 0;

//        return hasProgress ? categoryModelList.size() + 1 : categoryModelList.size();
        return 5;
    }


    public void disableProgress() {
        hasProgress = false;
    }

    public void activeProgress() {
        hasProgress = true;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        RowSearchCategoryBinding binding;

        public ViewHolder(RowSearchCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

    }
}
