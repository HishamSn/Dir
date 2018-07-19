package com.noventapp.direct.user.daos.remote.details;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.ClientInfoModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public class DetailsRemoteDao implements IDetailsRemoteDao {

    private static DetailsRemoteDao instance;
    private DetailsClient detailsClient;

    public DetailsRemoteDao() {
        detailsClient = HttpHelper.getInstance().create(DetailsClient.class);

    }

    public static synchronized DetailsRemoteDao getInstance() {
        if (instance == null) {
            instance = new DetailsRemoteDao();
        }


        return instance;
    }

    @Override
    public HttpCall<BaseGenericWrapper<ClientInfoModel>> getClientInfo(Integer clientId, Integer branchId) {
        Map<String, Object> map = new HashMap<>();
        map.put("branchId", branchId);
        return detailsClient.getInfo(clientId, map);
    }

    @Override
    public HttpCall<BaseWrapper> checkIn(Integer clientId) {
        Map<String, Object> map = new HashMap<>();
        map.put("checkIn", true);
        return detailsClient.checkIn(clientId, map);
    }

    private interface DetailsClient {
        @GET(ApiConstants.GET_CLIENT_INFO)
        HttpCall<BaseGenericWrapper<ClientInfoModel>> getInfo(@Path("id") Integer id
                , @QueryMap Map<String, Object> map);

        @POST(ApiConstants.CHECK_IN)
        HttpCall<BaseWrapper> checkIn(@Path("id") Integer id, @Body Map<String, Object> map);
    }
}
