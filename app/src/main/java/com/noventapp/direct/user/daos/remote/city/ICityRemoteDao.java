package com.noventapp.direct.user.daos.remote.city;

import com.noventapp.direct.user.daos.remote.IDao;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.CityModel;

import io.realm.RealmList;

public interface ICityRemoteDao extends IDao<CityModel> {

    HttpCall<BaseGenericWrapper<RealmList<CityModel>>> getList(Integer id);


}
