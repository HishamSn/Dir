package com.noventapp.direct.user.ui.details;

import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.details.info.InfoFragment;
import com.noventapp.direct.user.ui.details.offers.OffersFragment;
import com.noventapp.direct.user.ui.details.rating.RatingFragment;
import com.noventapp.direct.user.utils.FragmentUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.btn_back)
    AppCompatButton btnBack;
    @BindView(R.id.tb_fav)
    ToggleButton tbFav;
    @BindView(R.id.btn_checkIn)
    AppCompatButton btnCheckIn;
    @BindView(R.id.tv_views)
    AppCompatTextView tvViews;
    @BindView(R.id.tv_name)
    AppCompatTextView tvName;
    @BindView(R.id.tv_bio)
    AppCompatTextView tvBio;
    @BindView(R.id.rb_rating)
    AppCompatRatingBar rbRating;
    @BindView(R.id.tv_review)
    AppCompatTextView tvReview;
    @BindView(R.id.tv_placeStatus)
    AppCompatTextView tvPlaceStatus;
    @BindView(R.id.iv_phone)
    AppCompatImageView ivPhone;
    @BindView(R.id.iv_store)
    AppCompatImageView ivStore;
    @BindView(R.id.iv_location)
    AppCompatImageView ivLocation;
    @BindView(R.id.iv_instagram)
    AppCompatImageView ivInstagram;
    //    @BindView(R.id.ti_info)
//    TabItem tiInfo;
//    @BindView(R.id.ti_menu)
//    TabItem tiMenu;
//    @BindView(R.id.ti_offers)
//    TabItem tiOffers;
//    @BindView(R.id.ti_rating)
//    TabItem tiRating;

    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;

    TabItem tiInfo, tiMenu, tiOffers, tiRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        tiInfo = findViewById(R.id.ti_info);
        tiMenu = findViewById(R.id.ti_menu);
        tiOffers = findViewById(R.id.ti_offers);
        tiRating = findViewById(R.id.ti_rating);
        tlTabs.addOnTabSelectedListener(this);
        FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
                new InfoFragment(), R.id.fm_container);
    }

    @OnClick({R.id.btn_back, R.id.tb_fav, R.id.btn_checkIn, R.id.iv_phone, R.id.iv_store,
            R.id.iv_location, R.id.iv_instagram})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.tb_fav:
                break;
            case R.id.btn_checkIn:
                break;
            case R.id.iv_phone:
                break;
            case R.id.iv_store:
                break;
            case R.id.iv_location:
                break;
            case R.id.iv_instagram:
                break;
        }
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
                        new InfoFragment(), R.id.fm_container);
                break;
            case 1:
                Toast.makeText(this, "tab" + tab.getPosition(), Toast.LENGTH_LONG).show();
                break;
            case 2:
                FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
                        new OffersFragment(), R.id.fm_container);
                break;
            case 3:
                FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
                        new RatingFragment(), R.id.fm_container);
                break;
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
