package com.noventapp.direct.user.ui.details.branch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.noventapp.direct.user.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BranchListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_branch)
    RecyclerView rvBranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.our_branch);
        setRecyclerView();
    }

    private void setRecyclerView() {
        rvBranch.setAdapter(new BranchAdapter());
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
