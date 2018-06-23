package com.noventapp.direct.user.ui.details.offers;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.OfferModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.List;

public class OffersAdapter extends BaseAdapter<OffersAdapter.ViewHolder> {
    private static final int ROW_OFFER = R.layout.row_offer;
    List<OfferModel> offerList;

    @NonNull
    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new OffersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_OFFER;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
