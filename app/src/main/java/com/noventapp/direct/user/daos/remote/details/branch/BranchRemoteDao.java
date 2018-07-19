package com.noventapp.direct.user.daos.remote.details.branch;

import com.noventapp.direct.user.constants.ApiConstants;
import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.data.network.HttpHelper;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BranchModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;

public class BranchRemoteDao implements IBranchRemoteDao {

    private static BranchRemoteDao instance;
    private BranchClient branchClient;

    public BranchRemoteDao() {
        branchClient = HttpHelper.getInstance().create(BranchClient.class);

    }

    public static synchronized BranchRemoteDao getInstance() {
        if (instance == null) {
            instance = new BranchRemoteDao();
        }


        return instance;
    }

    @Override
    public HttpCall<BaseGenericWrapper<List<BranchModel>>> getBranchList(Integer clientId) {
        return branchClient.getBranchList(clientId);
    }

    private interface BranchClient {

        @GET(ApiConstants.GET_BRANCH_LIST)
        HttpCall<BaseGenericWrapper<List<BranchModel>>> getBranchList(@Path("id") Integer id);

    }
}
