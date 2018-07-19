package com.noventapp.direct.user.daos.remote.favorites;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;
import com.noventapp.direct.user.model.ClientModel;

import java.util.List;

public interface IFavoritesRemoteDao {
    HttpCall<BaseWrapper> addOrRemoveFav(Integer clientId, Boolean favFlag);

    HttpCall<BaseGenericWrapper<List<ClientModel>>> getFavList();
}
