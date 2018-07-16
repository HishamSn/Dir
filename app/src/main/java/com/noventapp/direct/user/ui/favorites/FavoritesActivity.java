package com.noventapp.direct.user.ui.favorites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.noventapp.direct.user.R;

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
      //  rvFavorites.setAdapter(new );
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
    }
}
