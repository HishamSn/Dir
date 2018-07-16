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
import com.noventapp.direct.user.model.FeaturedClient;
import com.noventapp.direct.user.ui.base.BaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;


public class FeaturedAdapter extends BaseAdapter<FeaturedAdapter.ViewHolder> {

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_featured_client;
    private Context context;
    private List<FeaturedClient> featuredClientList;
    private boolean hasProgress = true;

    public FeaturedAdapter(List<FeaturedClient> featuredClientList) {
        this.featuredClientList = featuredClientList;
    }

    public FeaturedAdapter() {
    }


    @Override
    public int getItemViewType(int position) {
//        if (position == featuredClientList.size() && hasProgress) {
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
//        if (position == featuredClientList.size()) {
//            return;
//        }
        try {
            loadImage(holder, featuredClientList.get(position).getCoverImage());
        } catch (Exception e) {

        }
        holder.tvClientName.setText(featuredClientList.get(position).getBaseCompanyName());
        holder.itemView.setOnClickListener(v -> {
        });


    }

    private void loadImage(ViewHolder holder, String url) throws Exception {
        Picasso.with(holder.ivClientCover.getContext()).load(url).into(holder.ivClientCover);
    }


    @Override
    public int getItemCount() {
        // if we don't need loading in the first
//        return featuredClientList.size() > 0 ?
//                hasProgress ? featuredClientList.size() + 1 : featuredClientList.size() : 0;

//        return hasProgress ? featuredClientList.size() + 1 : featuredClientList.size();
        return featuredClientList.size();
    }


    public void disableProgress() {
        hasProgress = false;
    }

    public void activeProgress() {
        hasProgress = true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivClientCover;
        AppCompatTextView tvClientName;


        public ViewHolder(View itemView) {
            super(itemView);
            ivClientCover = itemView.findViewById(R.id.iv_featured_client_cover);
            tvClientName = itemView.findViewById(R.id.tv_featured_client_name);


        }
    }
}
