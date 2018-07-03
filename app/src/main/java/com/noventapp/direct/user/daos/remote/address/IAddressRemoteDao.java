package com.noventapp.direct.user.daos.remote.address;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.AddressModel;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BaseWrapper;

import java.util.List;

public interface IAddressRemoteDao {

    HttpCall<BaseWrapper> createAddress(String addressName, String buildingNum, String floorNum,
                                        String apartmentNum, Double lat, Double lng, Integer customerId);

    HttpCall<BaseGenericWrapper<List<AddressModel>>> getAddressList(Integer id);

    HttpCall<BaseWrapper> deleteAddress(Integer addressId);

    HttpCall<BaseWrapper> updateAddress(Integer addressId, String addressName, String buildingNum
            , String floorNum, String apartmentNum, Double lat, Double lng, Integer customerId);

    HttpCall<BaseGenericWrapper<AddressModel>> getAddressInfo(Integer addressId);
}
