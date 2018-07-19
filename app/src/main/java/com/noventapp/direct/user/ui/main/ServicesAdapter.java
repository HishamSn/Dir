package com.noventapp.direct.user.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseAdapter;
import com.noventapp.direct.user.ui.details.DetailsActivity;

import java.util.List;

public class ServicesAdapter extends BaseAdapter<ServicesAdapter.ViewHolder> {

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_main_service;
    private Context context;
    private List<Object> categoryModelList;
    private boolean hasProgress = true;

    public ServicesAdapter(List<Object> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    public ServicesAdapter() {
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
//            ActivityOptionsCompat option = ActivityOptionsCompat
//                    .makeSceneTransitionAnimation((Activity) context, holder.ivMain,
//                            ViewCompat.getTransitionName(holder.ivMain));
            context.startActivity(intent);
//            context.startActivity(intent, option.toBundle());
            holder.itemView.post(() -> holder.itemView.setEnabled(true));

        });


    }

    @Override
    public int getItemCount() {
        // if we don't need loading in the first
//        return categoryModelList.size() > 0 ?
//                hasProgress ? categoryModelList.size() + 1 : categoryModelList.size() : 0;

//        return hasProgress ? categoryModelList.size() + 1 : categoryModelList.size();
        return 10;
    }

    public void disableProgress() {
        hasProgress = false;
    }

    public void activeProgress() {
        hasProgress = true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivMain;
        RecyclerView rvServices;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMain = itemView.findViewById(R.id.iv_restaurant);
//            rvServices = itemView.findViewById(R.id.rv_services);


        }
    }
}
