package com.noventapp.direct.user.ui.details.info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.model.ServiceModel;
import com.noventapp.direct.user.ui.base.BaseFragment;

import java.util.ArrayList;
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
    @BindView(R.id.rv_service)
    RecyclerView rvService;
    @BindView(R.id.rv_amenities)
    RecyclerView rvAmenities;
    Unbinder unbinder;
    List<ServiceModel> serviceList1 = new ArrayList<>();
    List<ServiceModel> serviceList2 = new ArrayList<>();


    public InfoFragment() {
        // Required empty public constructor
    }

     public static InfoFragment newInstance() {

         InfoFragment fragment = new InfoFragment();
         return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        ServiceModel ss = new ServiceModel();
        ss.setName("delivery");
        serviceList1.add(ss);
        serviceList1.add(ss);
        serviceList1.add(ss);
//        serviceList1.add(ss);
//        serviceList1.add(ss);
        rvService.setAdapter(new InfoAdapter(serviceList1));
        ServiceModel ss1 = new ServiceModel();
        ss.setName("delivery");
        serviceList2.add(ss1);
        ServiceModel ss2 = new ServiceModel();
        ss.setName("delivery");
        serviceList2.add(ss2);
        ServiceModel ss3 = new ServiceModel();
        ss.setName("delivery");
        serviceList2.add(ss3);
        rvAmenities.setAdapter(new InfoAdapter(serviceList2));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
