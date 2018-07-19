package com.noventapp.direct.user.daos.remote.favorites;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.ClientModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class FavoritesRemoteDao implements IFavoritesRemoteDao {
    private static FavoritesRemoteDao instance;
    private FavoritesClient favoritesClient;

    public FavoritesRemoteDao() {
        favoritesClient = HttpHelper.getInstance().create(FavoritesClient.class);
    }

    public static synchronized FavoritesRemoteDao getInstance() {
        if (instance == null) {
            instance = new FavoritesRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseWrapper> addOrRemoveFav(Integer clientId, Boolean favFlag) {
        Map<String, Object> map = new HashMap<>();
        map.put("clientId", clientId);
        map.put("isFavored", favFlag);
        return favoritesClient.addOrRemoveFav(map);
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<ClientModel>>> getFavList() {
        return favoritesClient.getFavList();
    }

    private interface FavoritesClient {
        @POST(ApiConstants.ADD_REMOVE_FAV)
        HttpCall<BaseWrapper> addOrRemoveFav(@Body Map<String, Object> map);

        @GET(ApiConstants.GET_FAV_LIST)
        HttpCall<BaseGenericWrapper<List<ClientModel>>> getFavList();
    }
}
