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
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.favorites.FavoritesRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.ClientModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;
import com.noventapp.direct.user.ui.details.DetailsActivity;
import com.noventapp.direct.user.utils.SessionUtils;
import com.noventapp.direct.user.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.List;

import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.FAILED;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.WARNING;


public class ClientAdapter extends BaseAdapter<ClientAdapter.ViewHolder> {

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_main_restaurant;
    boolean isMainActivity;
    private Context context;
    private List<ClientModel> clientModelList = new ArrayList<>();
    private boolean hasProgress = true;

    public ClientAdapter(List<ClientModel> clientModelList, boolean isMainActivity) {
        this.clientModelList = clientModelList;
        this.isMainActivity = isMainActivity;
//        notifyDataSetChanged();
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

        if (clientModelList.get(position).getCoverPhoto() != null) {
            try {
                loadImage(holder, clientModelList.get(position).getCoverPhoto());
            } catch (Exception e) {

            }
        }

        if (clientModelList.get(position).getSelfPickup()) {
            holder.ivTakeaway.setVisibility(View.VISIBLE);
        }
        if (clientModelList.get(position).getDelivery()) {
            holder.ivDelivery.setVisibility(View.VISIBLE);
        }
        if (clientModelList.get(position).getBooking()) {
            holder.ivDiscount.setVisibility(View.VISIBLE);
        }
        holder.tbFavorites.setChecked(clientModelList.get(position).getFavored());


        holder.itemView.setOnClickListener(v -> {
            holder.itemView.setEnabled(false);
            Intent intent = new Intent(context, DetailsActivity.class);
            ActivityOptionsCompat option = ActivityOptionsCompat
                    .makeSceneTransitionAnimation((Activity) context, holder.ivMain,
                            ViewCompat.getTransitionName(holder.ivMain));
            intent.putExtra("client_id", clientModelList.get(position).getClientId());
            intent.putExtra("branch_id", clientModelList.get(position).getBranchId());
            context.startActivity(intent, option.toBundle());
            holder.itemView.post(() -> holder.itemView.setEnabled(true));

        });

        holder.tbFavorites.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (SessionUtils.getInstance().isLogin()) {
                favoritesDao(clientModelList.get(position).getClientId(), isChecked, position);

            } else {
                SnackbarUtil.showDefaultSnackBar((Activity) context, context.getString(R.string.you_must_login)
                        , false, WARNING);
                holder.tbFavorites.setChecked(false);

            }
        });


    }

    private void loadImage(ClientAdapter.ViewHolder holder, String url) throws Exception {
        Glide.with(holder.ivMain).load(url).into(holder.ivMain);
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

    private void favoritesDao(Integer clientId, Boolean isChecked, Integer position) {
        FavoritesRemoteDao.getInstance().addOrRemoveFav(clientId, isChecked).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    if (isMainActivity) {
                        clientModelList.get(position).setFavored(isChecked);
                        notifyDataSetChanged();
                    } else {
                        //MAIN ACITIVTY
                        //   clientModelList.get(position).setFavored(flag);
                        clientModelList.remove(clientModelList.indexOf(clientModelList.get(position)));
                        notifyItemRemoved(clientModelList.indexOf(clientModelList.get(position)));
                        notifyItemRangeChanged(clientModelList.indexOf(clientModelList.get(position)), getItemCount());
                    }
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();

                    break;
                case HttpStatus.BAD_REQUEST:
                    SnackbarUtil.showDefaultSnackBar((Activity) context, result.getError().getMessage()
                            , false, FAILED);
                    break;
                case HttpStatus.NETWORK_ERROR:
                    SnackbarUtil.showDefaultSnackBar((Activity) context, context.getString(R.string.network_error),
                            true, false, R.string.try_again,
                            v -> favoritesDao(clientId, isChecked, position), WARNING);
                    break;
                case HttpStatus.SERVER_ERROR:
                    SnackbarUtil.showDefaultSnackBar((Activity) context, context.getString(R.string.server_error),
                            true, false, R.string.try_again,
                            v -> favoritesDao(clientId, isChecked, position), WARNING);
                    break;

                default:
                    SnackbarUtil.showDefaultSnackBar((Activity) context,
                            context.getString(R.string.unexpected_error), false, FAILED);
                    break;

            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView ivMain, ivDelivery, ivTakeaway, ivDiscount;
        //        RecyclerView rvServices;
        AppCompatTextView tvRestaurantName, tvDescription, tvTime, tvNumberOfRating;
        RatingBar ratingBar;
        ToggleButton tbFavorites;


        public ViewHolder(View itemView) {
            super(itemView);
            ivMain = itemView.findViewById(R.id.iv_restaurant);
//            rvServices = itemView.findViewById(R.id.rv_services);
            tvRestaurantName = itemView.findViewById(R.id.tv_title_restaurant);
            tvDescription = itemView.findViewById(R.id.tv_description_restaurant);
            tvTime = itemView.findViewById(R.id.tv_time);
            ratingBar = itemView.findViewById(R.id.rb_rate);
            tbFavorites = itemView.findViewById(R.id.tb_fav);
            ivDelivery = itemView.findViewById(R.id.iv_delivery);
            ivDiscount = itemView.findViewById(R.id.iv_discount);
            ivTakeaway = itemView.findViewById(R.id.iv_take_away);
            tvNumberOfRating = itemView.findViewById(R.id.tv_numberOfRating);


        }
    }
}
