package com.noventapp.direct.user.ui.favorites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.main.MainAdapter;
import com.noventapp.direct.user.utils.viewutil.RecyclerViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoritesActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.favorites);
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerViewUtil.addItemDecoration(rvFavorites, true);
        rvFavorites.setAdapter(new MainAdapter());

    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
    }
}
