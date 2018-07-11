package com.noventapp.direct.user.ui.feedback;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.daos.remote.contactus.ContactUsRemoteDao;
import com.noventapp.direct.user.daos.remote.feedback.FeedbackDao;
import com.noventapp.direct.user.data.network.HttpStatus;
import com.noventapp.direct.user.model.ContactUs;
import com.noventapp.direct.user.ui.base.BaseActivity;
import com.noventapp.direct.user.utils.DialogUtil;
import com.noventapp.direct.user.utils.SessionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FeedbackActivity extends BaseActivity implements Validator.ValidationListener {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @Length(min = 4, messageResId = R.string.msg_feedback)
    @BindView(R.id.et_feedback_msg)
    AppCompatEditText etFeedbackMsg;
    @BindView(R.id.rv_country)
    RecyclerView rvCountry;
    List<ContactUs> contactUsList = new ArrayList<>();
    Validator validator;

    private ContactUsAdapter contactUsAdapter;
    private SweetAlertDialog dialogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);

        toolbarTitle.setText(R.string.feed_back);
        dialogProgress = DialogUtil.progress(this);
        dialogProgress.show();
        setUpRecyclerView();
        contactRemoteDao();
    }

    private void contactRemoteDao() {
        ContactUsRemoteDao.getInstance().getContactUs().enqueue(result -> {
                    dialogProgress.dismiss();

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:

                            if (result.getResult().getCode() == 204 || result.getResult().getData().isEmpty()) {
                                contactUsList.clear();
//                                DialogUtil.errorMessage(this,
//                                        result.getResult().getMessage(), true);
                            } else {
                                contactUsList = result.getResult().getData();
                            }
                            contactUsAdapter.notifyDataSetChanged();
                            break;

                        case HttpStatus.BAD_REQUEST:
                            DialogUtil.errorMessage(this, result.getError().getMessage());
                            break;

                        case HttpStatus.SERVER_ERROR:
                            DialogUtil.errorMessage(this, getString(R.string.server_error));
                            break;

                        case HttpStatus.NETWORK_ERROR:
                            DialogUtil.errorMessage(this, getString(R.string.network_error));
                            break;

                        default:
                            DialogUtil.errorMessage(this, getString(R.string.unexpected_error));
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
                validator.validate();
        }


    }


    private void createFeedback(String feedBackContent) {
        FeedbackDao.getInstance().createFeedback(SessionUtils.getInstance().getUser().getId(), feedBackContent)
                .enqueue(result -> {

                    switch (result.getStatus()) {
                        case HttpStatus.SUCCESS:
                            dialogProgress.dismiss();
                            DialogUtil.successMessage(getString(R.string.success));
                            etFeedbackMsg.setText("");
//
//                            new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
//                                    .setTitleText(context.getString(R.string.success))
//                                    .setContentText(context.getString(R.string.logout_msg))
//                                    .setConfirmClickListener(sweetAlertDialog -> {
//                                        sweetAlertDialog.dismiss();
//                                    })
//                                    .show();

                            break;

                        case HttpStatus.BAD_REQUEST:
                            DialogUtil.errorMessage(this, result.getError().getMessage());
                            break;

                        case HttpStatus.SERVER_ERROR:
                            DialogUtil.errorMessage(this, getString(R.string.server_error));
                            break;

                        case HttpStatus.NETWORK_ERROR:
                            DialogUtil.errorMessage(this, getString(R.string.network_error));
                            break;

                        default:
                            DialogUtil.errorMessage(this, getString(R.string.unexpected_error));
                            break;
                    }

                });
    }


    @Override
    public void onValidationSucceeded() {
        dialogProgress.show();

        createFeedback(etFeedbackMsg.getText().toString());

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof TextInputEditText) {
                ((TextInputLayout) view.getParent().getParent()).setErrorEnabled(true);
                ((TextInputLayout) view.getParent().getParent()).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
