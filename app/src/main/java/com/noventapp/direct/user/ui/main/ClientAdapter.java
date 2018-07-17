package com.noventapp.direct.user.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.ClientModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;
import com.noventapp.direct.user.ui.details.DetailsActivity;

import java.util.ArrayList;
import java.util.List;


public class ClientAdapter extends BaseAdapter<ClientAdapter.ViewHolder> {

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_main_restaurant;
    private Context context;
    private List<ClientModel> clientModelList = new ArrayList<>();
    private boolean hasProgress = true;

    public ClientAdapter(List<ClientModel> clientModelList) {
        this.clientModelList = clientModelList;
        notifyDataSetChanged();
    }

    public ClientAdapter() {
    }


    @Override
    public int getItemViewType(int position) {
//        if (position == clientModelList.size() && hasProgress) {
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
        holder.tvRestaurantName.setText(clientModelList.get(position).getClientBaseName());
        holder.tvDescription.setText(clientModelList.get(position).getClientBaseSloganName());
        holder.tvNumberOfRating.setText("(" + clientModelList.get(position).getNumberOfRatings() + ")");
        holder.ratingBar.setRating(clientModelList.get(position).getOverWholeRating());
        if (clientModelList.get(position).getSelfPickup()) {
            holder.ivTakeaway.setVisibility(View.VISIBLE);
        }
        if (clientModelList.get(position).getDelivery()) {
            holder.ivDelivery.setVisibility(View.VISIBLE);
        }
        if (clientModelList.get(position).getBooking()) {
            holder.ivDiscount.setVisibility(View.VISIBLE);
        }


        holder.itemView.setOnClickListener(v -> {
            holder.itemView.setEnabled(false);
            Intent intent = new Intent(context, DetailsActivity.class);
            ActivityOptionsCompat option = ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) context, holder.ivMain,
                            ViewCompat.getTransitionName(holder.ivMain));
//            context.startActivity(intent);
            context.startActivity(intent, option.toBundle());
            holder.itemView.post(() -> holder.itemView.setEnabled(true));

        });


    }

    @Override
    public int getItemCount() {
        // if we don't need loading in the first
//        return clientModelList.size() > 0 ?
//                hasProgress ? clientModelList.size() + 1 : clientModelList.size() : 0;

//        return hasProgress ? clientModelList.size() + 1 : clientModelList.size();
        return clientModelList.size();
    }

    public void disableProgress() {
        hasProgress = false;
    }

    public void activeProgress() {
        hasProgress = true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivMain, ivDelivery, ivTakeaway, ivDiscount;
        //        RecyclerView rvServices;
        AppCompatTextView tvRestaurantName, tvDescription, tvTime, tvNumberOfRating;
        RatingBar ratingBar;
        ToggleButton favorites;


        public ViewHolder(View itemView) {
            super(itemView);
            ivMain = itemView.findViewById(R.id.iv_restaurant);
//            rvServices = itemView.findViewById(R.id.rv_services);
            tvRestaurantName = itemView.findViewById(R.id.tv_title_restaurant);
            tvDescription = itemView.findViewById(R.id.tv_description_restaurant);
            tvTime = itemView.findViewById(R.id.tv_time);
            ratingBar = itemView.findViewById(R.id.rb_rate);
            favorites = itemView.findViewById(R.id.tb_fav);
            ivDelivery = itemView.findViewById(R.id.iv_delivery);
            ivDiscount = itemView.findViewById(R.id.iv_discount);
            ivTakeaway = itemView.findViewById(R.id.iv_take_away);
            tvNumberOfRating = itemView.findViewById(R.id.tv_numberOfRating);


        }
    }
}
