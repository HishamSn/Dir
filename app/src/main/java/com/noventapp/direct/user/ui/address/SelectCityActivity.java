package com.noventapp.direct.user.ui.address;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.noventapp.direct.user.R;
import com.noventapp.direct.user.ui.address.adapter.CityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectCityActivity extends AppCompatActivity {


    @BindView(R.id.iv_back)
    AppCompatImageView ivBack;
    @BindView(R.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R.id.rv_city)
    RecyclerView rvCity;
    @BindView(R.id.et_search)
    AppCompatEditText etSearch;
    @BindView(R.id.btn_clear)
    AppCompatButton btnClear;
    @BindView(R.id.btn_search)
    AppCompatImageView btnSearch;
    private List<ParentObject> cityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        ButterKnife.bind(this);
        setUpReyclerView();
    }

    private void setUpReyclerView() {
        rvCity.setLayoutManager(new LinearLayoutManager(this));
        rvCity.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        CityAdapter cityAdapter = new CityAdapter(this, cityList);
        cityAdapter.setParentClickableViewAnimationDefaultDuration();
        cityAdapter.setParentAndIconExpandOnClick(true);
    }

//    private void setSampleList() {
//        CityCreator cityCreator = CityCreator.get(this);
//        List<String> List = cityCreator.getAll();
//        for(String  title:List)
//        {
//            List<Object> childList = new ArrayList<>();
//            childList.add(new TitleChild("Add to contacts","Send message"));
//            title.setChildObjectList(childList);
//            parentObject.add(title);
//        }
//        return parentObject;
//
//        cityList.add((ParentObject) "jordan");
//        cityList.add("Saudi Arabia");
//        cityList.add("Kuwait");
//        cityList.add("Qatar");
//        cityList.add()

    //  }
}
