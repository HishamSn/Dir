package com.noventapp.direct.user.ui.details.branch;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.BranchModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.List;

public class BranchAdapter extends BaseAdapter<BranchAdapter.ViewHolder> {
    private static final int ROW_BRANCH = R.layout.row_branch;
    private List<BranchModel> branchList;

    public BranchAdapter(List<BranchModel> branchList) {
        this.branchList = branchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new BranchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvBranchName.setText(branchList.get(position).getBaseBranchName());
        holder.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" +
                                branchList.get(position).getLatitude() + "," + branchList.get(position).getLongitude()));
                holder.btnLocation.getContext().startActivity(locationIntent);
            }
        });
        holder.rvDays.setAdapter(new DaysAdapter());
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_BRANCH;
    }


    @Override
    public int getItemCount() {
        return branchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvBranchName;
        AppCompatButton btnLocation;
        RecyclerView rvDays;

        public ViewHolder(View itemView) {
            super(itemView);
            tvBranchName = itemView.findViewById(R.id.tv_branchName);
            btnLocation = itemView.findViewById(R.id.btn_location);
            rvDays = itemView.findViewById(R.id.rv_days);
        }
    }
}
