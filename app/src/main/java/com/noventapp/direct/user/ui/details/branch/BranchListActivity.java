package com.noventapp.direct.user.ui.details.branch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.details.branch.BranchRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.BranchModel;
import com.noventapp.direct.user.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.FAILED;
import static com.noventapp.direct.user.utils.SnackbarUtil.SnackTypes.WARNING;

public class BranchListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_branch)
    RecyclerView rvBranch;
    Integer clientId;
    List<BranchModel> branchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list);
        ButterKnife.bind(this);
        toolbarTitle.setText(R.string.our_branch);
        clientId = getIntent().getExtras().getInt("client_id");
        init();
        branchDao(clientId);
    }

    private void init() {
        branchList = new ArrayList<>();
    }

    private void setRecyclerView() {
        rvBranch.setAdapter(new BranchAdapter(branchList));
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        onBackPressed();
    }


    private void branchDao(Integer clientId) {
        BranchRemoteDao.getInstance().getBranchList(clientId).enqueue(result -> {
            switch (result.getStatus()) {
                case HttpStatus.SUCCESS:
                    branchList.addAll(result.getResult().getData());
                    setRecyclerView();
                    break;
                case HttpStatus.BAD_REQUEST:
                    SnackbarUtil.showDefaultSnackBar(this, result.getError().getMessage()
                            , false, FAILED);
                    break;
                case HttpStatus.NETWORK_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.network_error),
                            true, false, R.string.try_again,
                            v -> branchDao(clientId), WARNING);
                    break;
                case HttpStatus.SERVER_ERROR:
                    SnackbarUtil.showDefaultSnackBar(this, getString(R.string.server_error),
                            true, false, R.string.try_again,
                            v -> branchDao(clientId), WARNING);
                    break;

                default:
                    SnackbarUtil.showDefaultSnackBar(this,
                            getString(R.string.unexpected_error), false, FAILED);
                    break;

            }
        });
    }
}
