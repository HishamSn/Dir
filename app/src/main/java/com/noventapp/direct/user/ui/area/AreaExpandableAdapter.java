package com.noventapp.direct.user.ui.area;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.noventapp.direct.user.R;
import com.noventapp.direct.user.data.prefs.PrefsUtils;
import com.noventapp.direct.user.model.AreaModel;
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class AreaExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<CityModel> cityBaseList = new ArrayList<>();
    private ArrayList<CityModel> cityOriginalList = new ArrayList<>();

    public AreaExpandableAdapter(Context context
            , List<CityModel> cityOriginalList) {
        this.context = context;
        this.cityBaseList.addAll(cityOriginalList);
        this.cityOriginalList.addAll(cityOriginalList);
    }

    @Override
    public int getGroupCount() {
        return cityBaseList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return cityBaseList.get(groupPosition).getAreaList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return cityBaseList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cityBaseList.get(groupPosition).getAreaList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CityModel parentRow = (CityModel) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_city, null);
        }

        final TextView tvCityName = convertView.findViewById(R.id.tv_city_name);

        tvCityName.setText(parentRow.getBaseCityName().trim());

//        bindView(convertView, mGroupData.get(groupPosition), mGroupFrom, mGroupTo);
//        ((ImageView) convertView.findViewById(R.id.videos_group_indicator))
//                .setImageResource(isExpanded?R.drawable.videos_chevron_expanded:R.drawable.videos_chevron_collapsed);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        AreaModel childRow = (AreaModel) getChild(groupPosition, childPosition);
        CityModel cityRow = (CityModel) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_area, null);
        }


        final TextView tvAreaName = convertView.findViewById(R.id.btn_area_name);
        tvAreaName.setText(childRow.getBaseAreaName().trim());


        tvAreaName.setOnClickListener(v -> {
            tvAreaName.setEnabled(false);
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("area_model", childRow);
            intent.putExtra("city_model", cityRow);
            context.startActivity(intent);
            tvAreaName.post(() -> tvAreaName.setEnabled(true));
            ((Activity) context).finish();
            PrefsUtils.getInstance().setFirstUse(false);

        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        cityBaseList.clear();

        if (query.isEmpty()) {
            cityBaseList.addAll(cityOriginalList);
        } else {
            for (CityModel cityRow : cityOriginalList) {

                List<AreaModel> areaList = cityRow.getAreaList();
                List<AreaModel> tempAreaList = new ArrayList<>();
                for (AreaModel areaRow : areaList) {
                    if (areaRow.getBaseAreaName().toLowerCase().contains(query)) {
                        tempAreaList.add(areaRow);
                    }
                }

                if (tempAreaList.size() > 0) {
                    CityModel nCityRow = new CityModel(cityRow.getBaseCityName(), tempAreaList);
                    cityBaseList.add(nCityRow);
                }
            }
        }

        notifyDataSetChanged();
    }
}
