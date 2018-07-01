package com.noventapp.direct.user.daos.remote.city;

import com.noventapp.direct.user.daos.remote.IDao;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.CityModel;

import java.util.List;

public interface ICityRemoteDao extends IDao<CityModel> {

    HttpCall<BaseGenericWrapper<List<CityModel>>> getList(Integer id);


}
