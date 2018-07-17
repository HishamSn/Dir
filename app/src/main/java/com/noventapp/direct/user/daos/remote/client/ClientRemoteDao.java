package com.noventapp.direct.user.daos.remote.client;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.ClientModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public class ClientRemoteDao implements IClientRemoteDao {

    private static ClientRemoteDao instance;
    private Client client;


    public ClientRemoteDao() {
        client = HttpHelper.getInstance().create(Client.class);
    }

    public static synchronized ClientRemoteDao getInstance() {
        if (instance == null) {
            instance = new ClientRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<ClientModel>>> getAllClient(Integer areaId) {
        return client.getAllClient(areaId);
    }


    private interface Client {
        @GET(ApiConstants.GET_ALL_CLIENT)
        HttpCall<BaseGenericWrapper<List<ClientModel>>> getAllClient(@Path("id") Integer id);
    }


}
