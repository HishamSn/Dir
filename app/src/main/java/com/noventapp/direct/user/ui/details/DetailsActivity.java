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
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.details.DetailsRemoteDao;
import com.noventapp.direct.user.daos.remote.favorites.FavoritesRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.ClientInfoModel;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.ui.details.branch.BranchListActivity;
import com.noventapp.direct.user.ui.details.info.InfoFragment;
import com.noventapp.direct.user.ui.details.menu.MenuFragment;
import com.noventapp.direct.user.ui.details.offers.OffersFragment;
import com.noventapp.direct.user.ui.details.rating.RatingFragment;
import com.noventapp.direct.user.utils.SessionUtils;
import com.noventapp.direct.user.utils.SnackbarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.FAILED;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.SUCCESS;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.WARNING;

public class DetailsActivity extends BaseActivity {

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
    Integer clientId, branchId;
    @BindView(R.id.iv_cover)
    AppCompatImageView ivCover;
    @BindView(R.id.tv_checkIn)
    AppCompatTextView tvCheckIn;
    ClientInfoModel clientInfoModel;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private SweetAlertDialog dialogProgress;


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
        //  dialogProgress = DialogUtil.progress(this);
        //  dialogProgress.show();
        getDataIntent();
        detailDao(clientId, branchId);
        setFavButton();

//        tlTabs.addOnTabSelectedListener(this);
//        FragmentUtil.showFragmentWithArguments(getSupportFragmentManager(),
//                new InfoFragment(), R.id.vp_container);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlTabs));
        tlTabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }


    private void getDataIntent() {
        clientId = getIntent().getExtras().getInt("client_id");
        branchId = getIntent().getExtras().getInt("branch_id");
    }

    private void setFavButton() {
        tbFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (SessionUtils.getInstance().isLogin()) {
                    favoritesDao(clientInfoModel.getId(), isChecked);

                } else {
                    SnackbarUtil.showDefaultSnackBar(DetailsActivity.this, getString(R.string.you_must_login)
                            , false, WARNING);
                    tbFav.setChecked(false);

                }
            }
        });
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
                if (SessionUtils.getInstance().isLogin()) {
                    checkIn(clientInfoModel.getId());
                } else {
                    SnackbarUtil.showDefaultSnackBar(DetailsActivity.this, getString(R.string.you_must_login)
                            , false, WARNING);
                }
                break;
            case R.id.iv_phone:
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + clientInfoModel.getServiceNumber()));
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                }
                break;
            case R.id.iv_store:
                Intent branchIntent = new Intent(this, BranchListActivity.class);
                branchIntent.putExtra("client_id", clientInfoModel.getId());
                startActivity(branchIntent);
                break;
            case R.id.iv_location:
                Intent locationIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + clientInfoModel.getLatitude() + "," + clientInfoModel.getLongtitude()));
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

    private void detailDao(Integer clientId, Integer branchId) {
        DetailsRemoteDao.getInstance().getClientInfo(clientId, branchId).enqueue(result -> {
            //   dialogProgress.dismiss();
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    clientInfoModel = result.getResult().getData();
                    tvBio.setText(clientInfoModel.getClientBaseSloganName());
                    tvName.setText(clientInfoModel.getClientBaseName());
                    tvViews.setText(clientInfoModel.getNumberOfViews() + " " + getString(R.string.views));
                    tvCheckIn.setText(clientInfoModel.getCheckInNumber() + " " + getString(R.string.check_in));
                    rbRating.setRating(clientInfoModel.getOverWholeRating());
                    tvReview.setText("(" + clientInfoModel.getNumberOfRatings() + getString(R.string.review) + ")");
                    if (clientInfoModel.getOpen()) {
                        tvPlaceStatus.setText(R.string.open_now);
                        tvPlaceStatus.setTextColor(getResources().getColor(R.color.open_text));
                    } else {
                        tvPlaceStatus.setText(R.string.closed);
                        tvPlaceStatus.setTextColor(getResources().getColor(R.color.error_red));
                    }

                    try {
                        loadImage(ivCover, clientInfoModel.getCoverImage());
                    } catch (Exception e) {

                    }
                    tbFav.setChecked(clientInfoModel.getFavored());
                    break;
            }
        });
    }

    private void favoritesDao(Integer clientId, Boolean flag) {
        FavoritesRemoteDao.getInstance().addOrRemoveFav(clientId, flag).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:

                    break;
                case HttpStatus.BAD_REQUEST:
                    SnackbarUtil.showDefaultSnackBar(this, result.getError().getMessage()
                            , false, FAILED);
                    break;
                case HttpStatus.NETWORK_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.network_error),
                            true, false, R.string.try_again,
                            v -> favoritesDao(clientId, flag), WARNING);
                    break;
                case HttpStatus.SERVER_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.server_error),
                            true, false, R.string.try_again,
                            v -> favoritesDao(clientId, flag), WARNING);
                    break;

                default:
                    SnackbarUtil.showDefaultSnackBar(this,
                            getString(R.string.unexpected_error), false, FAILED);
                    break;

            }
        });
    }

    private void checkIn(Integer clientId) {
        DetailsRemoteDao.getInstance().checkIn(clientId).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    SnackbarUtil.showDefaultSnackBar(this, result.getResult().getMessage()
                            , false, SUCCESS);
                    break;
                case HttpStatus.BAD_REQUEST:
                    SnackbarUtil.showDefaultSnackBar(this, result.getError().getMessage()
                            , false, FAILED);
                    break;
                case HttpStatus.NETWORK_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.network_error),
                            true, false, R.string.try_again,
                            v -> checkIn(clientId), WARNING);
                    break;
                case HttpStatus.SERVER_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.server_error),
                            true, false, R.string.try_again,
                            v -> checkIn(clientId), WARNING);
                    break;

                default:
                    SnackbarUtil.showDefaultSnackBar(this,
                            getString(R.string.unexpected_error), false, FAILED);
                    break;

            }
        });
    }


    private void loadImage(ImageView imageView, String url) throws Exception {
        Glide.with(imageView).load(url).into(imageView);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            Bundle bundle;
            switch (position) {
                case 0:
                    InfoFragment fragment = new InfoFragment();
                    bundle = new Bundle();
                    bundle.putInt("client_id", clientId);
                    bundle.putInt("branch_id", branchId);
                    fragment.setArguments(bundle);
                    return fragment;
                case 1:
                    MenuFragment fragment1 = new MenuFragment();
                    return fragment1;

                case 2:
                    OffersFragment fragment3 = new OffersFragment();
                    return fragment3;

                case 3:
                    RatingFragment fragment4 = new RatingFragment();
                    return fragment4;
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
