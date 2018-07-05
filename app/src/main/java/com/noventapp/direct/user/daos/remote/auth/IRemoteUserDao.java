package com.noventapp.direct.user.daos.remote.auth;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.TokenModel;

public interface IRemoteUserDao {
    HttpCall<BaseWrapper> signUp(String firstName, String lastName, String email,
                                 String password, String phoneNumber);

    HttpCall<TokenModel> login(String userName, String password);

    HttpCall<BaseWrapper> checkEmail(String emil);

    HttpCall<BaseWrapper> checkPhone(String phone);

    HttpCall<BaseWrapper> forgetPassword(String email);

}
