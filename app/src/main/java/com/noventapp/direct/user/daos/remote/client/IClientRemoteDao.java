package com.noventapp.direct.user.daos.remote.client;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.ClientModel;

import java.util.List;

public interface IClientRemoteDao {
    HttpCall<BaseGenericWrapper<List<ClientModel>>> getAllClient(Integer areaId);
}
