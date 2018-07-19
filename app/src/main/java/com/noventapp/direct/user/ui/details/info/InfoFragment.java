package com.noventapp.direct.user.ui.details.info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.details.DetailsRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.AmenitiesModel;
import com.noventapp.direct.user.model.ClientInfoModel;
import com.noventapp.direct.user.ui.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends BaseFragment {


    @BindView(R.id.btn_reserveTable)
    AppCompatButton btnReserveTable;
    @BindView(R.id.tv_description)
    AppCompatTextView tvDescription;
    @BindView(R.id.btn_showDescription)
    Button btnShowDescription;
    //    @BindView(R.id.rv_service)
//    RecyclerView rvService;
    @BindView(R.id.rv_amenities)
    RecyclerView rvAmenities;
    Unbinder unbinder;
    ClientInfoModel clientInfoModel;
    List<AmenitiesModel> amenitiesList;
    @BindView(R.id.iv_delivery)
    AppCompatImageView ivDelivery;
    @BindView(R.id.tv_delivery)
    AppCompatTextView tvDelivery;
    @BindView(R.id.iv_selfPickup)
    AppCompatImageView ivSelfPickup;
    @BindView(R.id.tv_selfPickup)
    AppCompatTextView tvSelfPickup;
    @BindView(R.id.iv_reservation)
    AppCompatImageView ivReservation;
    @BindView(R.id.tv_reservation)
    AppCompatTextView tvReservation;
    @BindView(R.id.cv_service)
    CardView cvService;
    @BindView(R.id.cv_amenities)
    CardView cvAmenities;
    Integer clientId, branchId;


    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        clientId = getArguments().getInt("client_id");
        branchId = getArguments().getInt("branch_id");
        detailDao(clientId, branchId);

        return view;
    }

    private void setAdapter() {
        rvAmenities.setAdapter(new InfoAdapter(amenitiesList));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void detailDao(Integer clientId, Integer branchId) {
        DetailsRemoteDao.getInstance().getClientInfo(clientId, branchId).enqueue(result -> {
            //   dialogProgress.dismiss();
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    clientInfoModel = result.getResult().getData();
                    amenitiesList = result.getResult().getData().getAmenities();
                    tvDescription.setText(clientInfoModel.getBaseDescription());
                    if (clientInfoModel.getHasDevlivery() || clientInfoModel.getHasBooking() || clientInfoModel.getHasSelfPickup()) {
                        cvService.setVisibility(View.VISIBLE);
                        if (clientInfoModel.getHasDevlivery()) {
                            ivDelivery.setVisibility(View.VISIBLE);
                            tvDelivery.setVisibility(View.VISIBLE);
                        }
                        if (clientInfoModel.getHasBooking()) {
                            ivReservation.setVisibility(View.VISIBLE);
                            tvReservation.setVisibility(View.VISIBLE);
                        }
                        if (clientInfoModel.getHasSelfPickup()) {
                            ivSelfPickup.setVisibility(View.VISIBLE);
                            tvSelfPickup.setVisibility(View.VISIBLE);
                        }
                    }
                    if (!amenitiesList.isEmpty()) {
                        cvAmenities.setVisibility(View.VISIBLE);
                        setAdapter();
                    }

                    break;
            }
        });
    }
}
