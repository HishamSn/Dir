package com.noventapp.direct.user.daos.remote.setting;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.UserModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class SettingRemoteDao implements ISettingRemoteDao {

    private static SettingRemoteDao instance;
    private SettingClient settingClient;

    public SettingRemoteDao() {
        settingClient = HttpHelper.getInstance().create(SettingClient.class);

    }

    public static synchronized SettingRemoteDao getInstance() {
        if (instance == null) {
            instance = new SettingRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseGenericWrapper<UserModel>> getUserInfo() {
        return settingClient.getUserInfo();
    }

    @Override
    public HttpCall<BaseWrapper> updateUserInfo(String firstName, String lastName,
                                                String email, String phone, String userName) {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("phoneNumber", phone);
        map.put("username", userName);
        map.put("email", email);
        return settingClient.updateUserInfo(map);
    }

    @Override
    public HttpCall<BaseWrapper> checkUserName(String userName) {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        return settingClient.checkUserName(map);
    }

    @Override
    public HttpCall<BaseWrapper> changePassword(Integer customerId, String oldPassword, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("oldPassword", oldPassword);
        map.put("newPassword", newPassword);
        return settingClient.changePassword(customerId, map);
    }

    private interface SettingClient {

        @GET(ApiConstants.GET_USER_INFO)
        HttpCall<BaseGenericWrapper<UserModel>> getUserInfo();

        @PUT(ApiConstants.UPDATE_USER_INFO)
        HttpCall<BaseWrapper> updateUserInfo(@Body Map<String, String> map);

        @POST(ApiConstants.VALIDATE_USER_NAME)
        HttpCall<BaseWrapper> checkUserName(@Body Map<String, Object> map);

        @POST(ApiConstants.CHANGE_PASSWORD)
        HttpCall<BaseWrapper> changePassword(@Path("id") Integer id, @Body Map<String, Object> map);

    }

}
