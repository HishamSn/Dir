package com.noventapp.direct.user.ui.details.branch;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseAdapter;

public class BranchAdapter extends BaseAdapter<BranchAdapter.ViewHolder> {
    private static final int ROW_BRANCH = R.layout.row_branch;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType,
                parent, false);
        return new BranchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.rvDays.setAdapter(new DaysAdapter());
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_BRANCH;
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvBranchName, tvLocation;
        RecyclerView rvDays;

        public ViewHolder(View itemView) {
            super(itemView);
            tvBranchName = itemView.findViewById(R.id.tv_branchName);
            tvLocation = itemView.findViewById(R.id.tv_location);
            rvDays = itemView.findViewById(R.id.rv_days);
        }
    }
}
