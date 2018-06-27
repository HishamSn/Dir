package com.noventapp.direct.user.daos.remote.address;

import com.google.android.gms.maps.model.Marker;
import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseWrapper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.POST;

public class AddressRemoteDao implements IAddressRemoteDao {

    private static AddressRemoteDao instance;
    private AddressClient addressClient;

    public AddressRemoteDao() {
        addressClient = HttpHelper.getInstance().create(AddressClient.class);

    }

    public static synchronized AddressRemoteDao getInstance() {
        if (instance == null) {
            instance = new AddressRemoteDao();
        }
        return instance;
    }

    @Override
    public HttpCall<BaseWrapper> createAddress(String addressName, Integer buildingNum
            , Integer floorNum, Integer apartmentNum, Double lat, Double lng, Integer customerId) {

        Map<String,Object> map = new HashMap<>();
        map.put("addressName",addressName);
        map.put("buildingNumber",buildingNum);
        map.put("floorNumber",floorNum);
        map.put("apartmentNumber",apartmentNum);
        map.put("latitude",lat);
        map.put("longtitude",lng);
        map.put("customerId",customerId);
        return addressClient.createAddress(map);
    }

    private interface AddressClient
    {
        @POST(ApiConstants.CREATE_ADDRESS)
        HttpCall<BaseWrapper> createAddress(@Body Map<String,Object> map);
    }
}
