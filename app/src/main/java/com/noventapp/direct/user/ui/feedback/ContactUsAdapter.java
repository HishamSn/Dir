package com.noventapp.direct.user.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.ContactUs;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.List;


public class ContactUsAdapter extends BaseAdapter<ContactUsAdapter.ViewHolder> {

    private static final int ROW_REFRESH = R.layout.row_progress;
    private static final int ROW_CATEGORY = R.layout.row_feedback;
    private Context context;
    private List<ContactUs> contactUsList;
    private boolean hasProgress = true;

    public ContactUsAdapter(List<ContactUs> contactUsList) {
        this.contactUsList = contactUsList;
    }




    @Override
    public int getItemViewType(int position) {
//        if (position == contactUsList.size() && hasProgress) {
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
//        if (position == contactUsList.size()) {
//            return;
//        }

        holder.tvCountryName.setText(contactUsList.get(position).getBaseCountryName());
        holder.tvPhoneNumber.setText(contactUsList.get(position).getContactNumber());
        holder.itemView.setOnClickListener(v -> {
            holder.itemView.setEnabled(false);
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + contactUsList.get(position).getContactNumber()));
            context.startActivity(intent);
            holder.itemView.post(() -> holder.itemView.setEnabled(true));

        });

    }

    @Override
    public int getItemCount() {
        // if we don't need loading in the first
//        return contactUsList.size() > 0 ?
//                hasProgress ? contactUsList.size() + 1 : contactUsList.size() : 0;

//        return hasProgress ? contactUsList.size() + 1 : contactUsList.size();
        return contactUsList.size();
    }

    public void disableProgress() {
        hasProgress = false;
    }

    public void activeProgress() {
        hasProgress = true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCountryName;
        private TextView tvPhoneNumber;


        public ViewHolder(View itemView) {
            super(itemView);

            tvCountryName = itemView.findViewById(R.id.tv_country_name);
            tvPhoneNumber = itemView.findViewById(R.id.tv_phone_number);


        }
    }
}
