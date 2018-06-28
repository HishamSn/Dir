package com.noventapp.direct.user.ui.feedback;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.contactus.ContactUsRemoteDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.ContactUs;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.utils.DialogProgressUtil;
import com.noventapp.direct.user.utils.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_feedback_msg)
    AppCompatEditText etFeedbackMsg;
    @BindView(R.id.rv_country)
    RecyclerView rvCountry;
    List<ContactUs> contactUsList = new ArrayList<>();


    private ContactUsAdapter contactUsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        toolbarTitle.setText(R.string.feed_back);
        DialogProgressUtil.getInstance(true).show();

        setUpRecyclerView();


        ContactUsRemoteDao.getInstance().getContactUs().enqueue(result ->

                {

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:
                            DialogProgressUtil.getInstance().dismiss();

                            if (result.getResult().getCode() == 203 || result.getResult().getData().isEmpty()) {
                                contactUsList.clear();
                                DialogUtil.errorMessage(this,
                                        result.getResult().getMessage(), true);
                            } else {
                                contactUsList = result.getResult().getData();
                            }
                            contactUsAdapter.notifyDataSetChanged();
                            break;

                        case HttpStatus.BAD_REQUEST:
                            DialogUtil.errorMessage(this, result.getError().getMessage());
                            DialogProgressUtil.getInstance().dismiss();
                            break;

                        case HttpStatus.SERVER_ERROR:
                            DialogUtil.errorMessage(this, getString(R.string.server_error));
                            DialogProgressUtil.getInstance().dismiss();
                            break;

                        case HttpStatus.NETWORK_ERROR:
                            DialogUtil.errorMessage(this, getString(R.string.network_error));
                            DialogProgressUtil.getInstance().dismiss();
                            break;

                        default:
                            DialogUtil.errorMessage(this, getString(R.string.unexpected_error));
                            DialogProgressUtil.getInstance().dismiss();
                            break;
                    }

                }
        );
    }

    private void setUpRecyclerView() {
        contactUsAdapter = new ContactUsAdapter(contactUsList);
        rvCountry.setLayoutManager(new LinearLayoutManager(this));
        rvCountry.setAdapter(contactUsAdapter);
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
