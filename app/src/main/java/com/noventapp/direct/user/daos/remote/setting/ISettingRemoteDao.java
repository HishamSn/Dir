package com.noventapp.direct.user.daos.remote.setting;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.UserModel;

public interface ISettingRemoteDao {
    HttpCall<BaseGenericWrapper<UserModel>> getUserInfo();

    HttpCall<BaseWrapper> updateUserInfo(String firstName, String lastName,
                                         String email, String phone, String userName);

    HttpCall<BaseWrapper> checkUserName(String name);

    HttpCall<BaseWrapper> changePassword(Integer customerId, String oldPassword, String newPassword);


}
