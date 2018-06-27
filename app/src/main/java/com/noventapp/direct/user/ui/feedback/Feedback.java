package com.noventapp.direct.user.ui.feedback;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Feedback extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_feedback_msg)
    AppCompatEditText etFeedbackMsg;
    @BindView(R.id.rv_country)
    RecyclerView rvCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.feed_back);

        setUpRecyclerView();

    }

    private void setUpRecyclerView() {
        rvCountry.setLayoutManager(new LinearLayoutManager(this));
        rvCountry.setAdapter(new FeedbackAdapter());
    }


    @OnClick({R.id.btn_back, R.id.btn_feedback_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_feedback_send:
                break;
        }
    }
}
