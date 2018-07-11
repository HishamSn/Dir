package com.noventapp.direct.user.daos.remote.client;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.ClientModel;

import java.util.List;

import retrofit2.http.GET;

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
    public HttpCall<BaseGenericWrapper<List<ClientModel>>> getFeaturedClient() {
        return client.getFeaturedClient();
    }

    private interface Client {
        @GET(ApiConstants.GET_FEATURED_CLIENT)
        HttpCall<BaseGenericWrapper<List<ClientModel>>> getFeaturedClient();
    }


}
