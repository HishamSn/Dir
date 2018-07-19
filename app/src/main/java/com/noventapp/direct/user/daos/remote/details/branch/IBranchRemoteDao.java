package com.noventapp.direct.user.daos.remote.details.branch;

import com.noventapp.direct.user.data.network.HttpCall;
import com.noventapp.direct.user.model.BaseGenericWrapper;
import com.noventapp.direct.user.model.BranchModel;

import java.util.List;

public interface IBranchRemoteDao {
    HttpCall<BaseGenericWrapper<List<BranchModel>>> getBranchList(Integer clientId);
}
