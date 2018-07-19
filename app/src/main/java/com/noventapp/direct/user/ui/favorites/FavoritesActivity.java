package com.noventapp.direct.user.ui.favorites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.favorites.FavoritesRemoteDao;
import com.noventapp.direct.user.data.db.DBHelper;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.CityAreaModel;
import com.noventapp.direct.user.model.ClientModel;
import com.noventapp.direct.user.ui.main.ClientAdapter;
import com.noventapp.direct.user.utils.SnackbarUtil;
import com.noventapp.direct.user.utils.viewutil.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.FAILED;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.WARNING;

public class FavoritesActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;
    List<ClientModel> favList;
    private CityAreaModel cityAreaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.favorites);
        init();
        getAreaCityDB();
        getFavList();

    }

    private void init() {
        favList = new ArrayList<>();
    }

    private void setRecyclerView() {
        RecyclerViewUtil.addItemDecoration(rvFavorites, true);
        rvFavorites.setAdapter(new ClientAdapter(favList, false));

    }

    private void getAreaCityDB() {
        cityAreaModel = DBHelper.getInstance().getFirst(CityAreaModel.class);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
    }

    private void getFavList() {
        FavoritesRemoteDao.getInstance().getFavList().enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    if (result.getResult().getCode() != 204) {
                        favList.addAll(result.getResult().getData());
                        setRecyclerView();

                    } else {
                        SnackbarUtil.showDefaultSnackBar(this, getString(R.string.empty_data), false, WARNING);
                    }
                    break;
                case HttpStatus.BAD_REQUEST:
                    SnackbarUtil.showDefaultSnackBar(this, result.getError().getMessage()
                            , false, FAILED);
                    break;
                case HttpStatus.NETWORK_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.network_error),
                            true, false, R.string.try_again,
                            v -> getFavList(), WARNING);
                    break;
                case HttpStatus.SERVER_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.server_error),
                            true, false, R.string.try_again,
                            v -> getFavList(), WARNING);
                    break;

                default:
                    SnackbarUtil.showDefaultSnackBar(this,
                            getString(R.string.unexpected_error), false, FAILED);
                    break;
            }
        });

    }

}
