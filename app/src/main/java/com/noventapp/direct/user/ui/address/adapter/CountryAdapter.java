package com.noventapp.direct.user.ui.address.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.ArrayList;

public class CountryAdapter extends BaseAdapter<CountryAdapter.ViewHolder> {
    private static final int ROW_COUNTRY = R.layout.row_country;
    private ArrayList<String> countryNameTest = new ArrayList<>();

    public CountryAdapter(ArrayList<String> countryNameTest) {
        this.countryNameTest = countryNameTest;
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
        holder.tv_name.setText(countryNameTest.get(position));

    }

    @Override
    public int getItemCount() {
        return countryNameTest.size();
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_COUNTRY;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);


        }
    }
}

