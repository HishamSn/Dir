package com.noventapp.direct.user.ui.address;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.address.AddressRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.AddressModel;
import com.noventapp.direct.user.ui.base.BaseAdapter;

import java.util.List;

public class AddressAdapter extends BaseAdapter<AddressAdapter.ViewHolder> {

    private static final int ROW_ADDRESS = R.layout.row_address;
    List<AddressModel> addressList;

    public AddressAdapter(List<AddressModel> addressList) {
        this.addressList = addressList;
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
        holder.tvAddressName.setText(addressList.get(position).getAddressName());
        // address is missing from api

    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return ROW_ADDRESS;
    }

    private void deleteAddressDao(Integer id, int position) {
        AddressRemoteDao.getInstance().deleteAddress(id).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    addressList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                    break;
                case HttpStatus.BAD_REQUEST:
                    break;
                case HttpStatus.NETWORK_ERROR:
                    break;
                case HttpStatus.SERVER_ERROR:
                    break;

            }

        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvAddressName, tvAddress;
        private AppCompatButton btnEdit, btnDelete, btnSelect;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAddressName = itemView.findViewById(R.id.tv_addressName);
            tvAddress = itemView.findViewById(R.id.tv_address);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnSelect = itemView.findViewById(R.id.btn_select);

            btnSelect.setOnClickListener(v -> {

            });
            btnEdit.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), AddressMapActivity.class);
                intent.putExtra("address_id", addressList.get(getPosition()).getId());
                intent.putExtra("address_lat", addressList.get(getPosition()).getLatitude());
                intent.putExtra("address_lng", addressList.get(getPosition()).getLongtitude());
                itemView.getContext().startActivity(intent);

            });

            btnDelete.setOnClickListener(v -> deleteAddressDao(addressList.get(getPosition()).getId(),
                    addressList.indexOf(addressList.get(getPosition()))));
        }
    }


}
