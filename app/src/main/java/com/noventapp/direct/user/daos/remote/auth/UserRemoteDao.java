package com.noventapp.direct.user.daos.remote.auth;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseWrapper;

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


    public HttpCall<BaseWrapper> signUpAuto() {
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", "hisham");
        map.put("lastName", "snaimeh");
        map.put("phoneNumber", "+962797611260");
        map.put("username", "hishamsn3");
        map.put("password", "123123123");
        map.put("email", "hish.sn2@gmail.com");
        return userClient.signUp(map);
    }

    private interface UserClient {
        @POST(ApiConstants.USER_SIGN_UP)
        HttpCall<BaseWrapper> signUp(@Body Map<String, Object> map);
    }
}
