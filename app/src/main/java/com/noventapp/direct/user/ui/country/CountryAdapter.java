package com.noventapp.direct.user.ui.country;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.data.db.DBHelper;
import com.noventapp.direct.user.data.prefs.PrefsUtils;
import com.noventapp.direct.user.model.CountryModel;
import com.noventapp.direct.user.ui.area.SelectAreaActivity;
import com.noventapp.direct.user.ui.base.BaseAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends BaseAdapter<CountryAdapter.ViewHolder> {
    private static final int ROW_COUNTRY = R.layout.row_country;
    private List<CountryModel> countryList = new ArrayList<>();
    private CountryModel countryModel;


    public CountryAdapter(List<CountryModel> countryList) {
        this.countryList = countryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(countryList.get(position).getBaseCountyName());
        try {
            loadImage(holder, countryList.get(position).getIconUrl());
        } catch (Exception e) {

        }
        holder.itemView.setOnClickListener(v -> {
            holder.itemView.setEnabled(false);
            DBHelper.getInstance().insertOrUpdate(countryList.get(position));
            PrefsUtils.getInstance().setCountryId(countryList.get(position).getId());
            Intent intent = new Intent(holder.itemView.getContext(), SelectAreaActivity.class);
            holder.itemView.getContext().startActivity(intent);
            holder.itemView.post(() -> holder.itemView.setEnabled(true));
        });


    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_COUNTRY;
    }

    private void loadImage(ViewHolder holder, String url) throws Exception {
        Picasso.with(holder.ivCountyImage.getContext()).load(url).into(holder.ivCountyImage);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tvName;
        private RoundedImageView ivCountyImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivCountyImage = itemView.findViewById(R.id.iv_country);
        }
    }

}

