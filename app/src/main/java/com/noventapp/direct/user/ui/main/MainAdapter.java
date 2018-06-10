package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseAdapter;
import com.noventapp.direct.user.ui.details.DetailsActivity;

import java.util.List;


public class MainAdapter extends BaseAdapter<MainAdapter.ViewHolder> {

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_main_restaurant;
    private Context context;
    private List<Object> categoryModelList;
    private boolean hasProgress = true;

    public MainAdapter(List<Object> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public MainAdapter() {
    }


    @Override
    public int getItemViewType(int position) {
//        if (position == categoryModelList.size() && hasProgress) {
//            return ROW_REFRESH;
//        }
        return ROW_CATEGORY;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        context = recyclerView.getContext();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        if (position == categoryModelList.size()) {
//            return;
//        }


        holder.itemView.setOnClickListener(v -> {
            holder.itemView.setEnabled(false);
            Intent intent = new Intent(context, DetailsActivity.class);
            context.startActivity(intent);
            holder.itemView.post(() -> holder.itemView.setEnabled(true));

        });

    }

    @Override
    public int getItemCount() {
        // if we don't need loading in the first
//        return categoryModelList.size() > 0 ?
//                hasProgress ? categoryModelList.size() + 1 : categoryModelList.size() : 0;

//        return hasProgress ? categoryModelList.size() + 1 : categoryModelList.size();
        return 4;
    }


    public void disableProgress() {
        hasProgress = false;
    }

    public void activeProgress() {
        hasProgress = true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);


        }
    }
}
