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
import com.noventapp.direct.user.model.CityModel;
import com.noventapp.direct.user.model.AreaModel;
import com.noventapp.direct.user.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class AreaExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<CityModel> cityModelList = new ArrayList<>();
    private ArrayList<CityModel> cityOriginalList = new ArrayList<>();

    public AreaExpandableAdapter(Context context
            , ArrayList<CityModel> cityOriginalList) {
        this.context = context;
        this.cityModelList.addAll(cityOriginalList);
        this.cityOriginalList.addAll(cityOriginalList);
    }

    @Override
    public int getGroupCount() {
        return cityModelList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return cityModelList.get(groupPosition).getDistrictList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return cityModelList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cityModelList.get(groupPosition).getDistrictList().get(childPosition);
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

        TextView tvCityName = convertView.findViewById(R.id.tv_city_name);

        tvCityName.setText(parentRow.getCityName().trim());


//        bindView(convertView, mGroupData.get(groupPosition), mGroupFrom, mGroupTo);
//        ((ImageView) convertView.findViewById(R.id.videos_group_indicator))
//                .setImageResource(isExpanded?R.drawable.videos_chevron_expanded:R.drawable.videos_chevron_collapsed);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        AreaModel childRow = (AreaModel) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_district, null);
        }


        final TextView tvDistrictName = convertView.findViewById(R.id.tv_district_name);
        tvDistrictName.setText(childRow.getBaseAreaName());


        tvDistrictName.setOnClickListener(v -> {
            tvDistrictName.setEnabled(false);
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("AREA_ID", childRow.getId());
            context.startActivity(intent);
            tvDistrictName.post(() -> tvDistrictName.setEnabled(true));
            ((Activity) context).finish();


        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query) {
        query = query.toLowerCase();
        cityModelList.clear();

        if (query.isEmpty()) {
            cityModelList.addAll(cityOriginalList);
        } else {
            for (CityModel parentRow : cityOriginalList) {
                List<AreaModel> childList = parentRow.getDistrictList();
                ArrayList<AreaModel> newList = new ArrayList<AreaModel>();

                for (AreaModel childRow : childList) {
                    if (childRow.getBaseAreaName().toLowerCase().contains(query)) {
                        newList.add(childRow);
                    }
                } // end for (com.example.user.searchviewexpandablelistview.ChildRow childRow: childList)
                if (newList.size() > 0) {
                    CityModel nParentRow = new CityModel(parentRow.getCityName(), newList);
                    cityModelList.add(nParentRow);
                }
            } // end or (com.example.user.searchviewexpandablelistview.ParentRow parentRow : cityOriginalList)
        } // end else

        notifyDataSetChanged();
    }
}
