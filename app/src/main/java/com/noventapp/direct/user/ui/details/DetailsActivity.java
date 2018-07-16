package com.noventapp.direct.user.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ToggleButton;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.details.branch.BranchListActivity;
import com.noventapp.direct.user.ui.details.info.InfoFragment;
import com.noventapp.direct.user.ui.details.menu.MenuFragment;
import com.noventapp.direct.user.ui.details.offers.OffersFragment;
import com.noventapp.direct.user.ui.details.rating.RatingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity  {

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
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    TabItem tiInfo, tiMenu, tiOffers, tiRating;
    ViewPager viewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        tiInfo = findViewById(R.id.ti_info);
        tiMenu = findViewById(R.id.ti_menu);
        tiOffers = findViewById(R.id.ti_offers);
        tiRating = findViewById(R.id.ti_rating);
        viewPager = findViewById(R.id.vp_container);
//        tlTabs.addOnTabSelectedListener(this);
//        FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
//                new InfoFragment(), R.id.vp_container);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlTabs));
        tlTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
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
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" +
                        "0797308365"));
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                }
                break;
            case R.id.iv_store:
                Intent branchIntent = new Intent(this, BranchListActivity.class);
                startActivity(branchIntent);
                break;
            case R.id.iv_location:
                Intent locationIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + 31.986763 + "," + 35.906471));
                startActivity(locationIntent);
                break;
            case R.id.iv_instagram:
                break;
        }
    }


//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        switch (tab.getPosition()) {
//            case 0:
//                FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
//                        new InfoFragment(), R.id.vp_container);
//                break;
//            case 1:
//                FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
//                        new MenuFragment(), R.id.vp_container);
//                break;
//            case 2:
//                FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
//                        new OffersFragment(), R.id.vp_container);
//                break;
//            case 3:
//                FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
//                        new RatingFragment(), R.id.vp_container);
//                break;
//        }
//
//    }

//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return InfoFragment.newInstance();

                case 1:
                    return MenuFragment.newInstance();

                case 2:
                    return OffersFragment.newInstance();

                case 3:
                    return RatingFragment.newInstance();

                default:
                    return null;

            }

        }

        @Override
        public int getCount() {

            return 4;
        }
    }
}
