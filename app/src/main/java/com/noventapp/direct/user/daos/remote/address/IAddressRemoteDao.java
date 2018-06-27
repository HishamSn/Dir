package com.noventapp.direct.user.daos.remote.address;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseWrapper;

public interface IAddressRemoteDao {

    HttpCall<BaseWrapper> createAddress(String addressName);
//    HttpCall<> getAddressList();
//    HttpCall<> deleteAddress();
//    HttpCall<> updateAddress();
}
