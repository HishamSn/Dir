package com.noventapp.direct.user.daos.remote.auth;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.TokenModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;

public class UserRemoteDao implements IRemoteUserDao {

    private static UserRemoteDao instance;
    private UserClient userClient;

    public UserRemoteDao() {
        userClient = HttpHelper.getInstance().create(UserClient.class);
    }

    public static synchronized UserRemoteDao getInstance() {
        if (instance == null) {
            instance = new UserRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseWrapper> signUp(String firstName, String latName, String email,
                                        String password, String phoneNumber) {
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", latName);
        map.put("phoneNumber", phoneNumber);
        map.put("username", "tasneem");
        map.put("email", email);
        map.put("password", password);
        return userClient.signUp(map);
    }

    @Override
    public HttpCall<TokenModel> login(String userName, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("usernameOrEmail", userName);
        map.put("password", password);
        return userClient.login(map);
    }

    private interface UserClient {
        @POST(ApiConstants.USER_SIGN_UP)
        HttpCall<BaseWrapper> signUp(@Body Map<String, Object> map);

        @POST(ApiConstants.USER_LOGIN)
        HttpCall<TokenModel> login(@Body Map<String, Object> map);
    }
}
