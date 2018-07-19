package com.noventapp.direct.user.daos.remote.details;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.ClientInfoModel;

public interface IDetailsRemoteDao {
    HttpCall<BaseGenericWrapper<ClientInfoModel>> getClientInfo(Integer clientId, Integer branchId);

    HttpCall<BaseWrapper> checkIn(Integer clientId);

}
