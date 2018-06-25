package com.noventapp.direct.user.ui.details.rating;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatingFragment extends BaseFragment {


    @BindView(R.id.iv_user)
    RoundedImageView ivUser;
    @BindView(R.id.et_opinion)
    AppCompatEditText etOpinion;
    @BindView(R.id.btn_rate)
    AppCompatButton btnRate;
    @BindView(R.id.btn_tip)
    AppCompatButton btnTip;
    @BindView(R.id.rv_rating)
    RecyclerView rvRating;
    Unbinder unbinder;

    public RatingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        unbinder = ButterKnife.bind(this, view);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        rvRating.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvRating.setAdapter(new RatingAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
