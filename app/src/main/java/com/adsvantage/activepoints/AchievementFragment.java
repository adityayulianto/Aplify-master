package com.adsvantage.activepoints;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.adsvantage.activepoints.R;
import com.adsvantage.activepoints.helper.RowItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievementFragment extends Fragment {

    String[] badgeNames;
    TypedArray badgeIds;
    String[] badgesInfo;

    public AchievementFragment() {
        // Required empty public constructor
    }

    List<RowItem> rowItems;
    ListView myListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_achievement, container, false);

        rowItems = new ArrayList<RowItem>();

        badgeNames = getResources().getStringArray(R.array.badgesName);

        badgeIds = getResources().obtainTypedArray(R.array.badgesId);

        badgesInfo = getResources().getStringArray(R.array.badgesInfo);

          for (int i = 0; i < badgeNames.length; i++) {
            RowItem item = new RowItem(badgeNames[i],
                    badgesInfo[i],
                    badgeIds.getResourceId(i, -1));
            rowItems.add(item);
        }

        myListView = (ListView) rootView.findViewById(R.id.lvList);
        CustomAdapter adapter = new CustomAdapter(this.getActivity().getApplicationContext(), rowItems);
        myListView.setAdapter(adapter);

        //myListView.setOnItemClickListener(this);

        return rootView;
    }

    public class CustomAdapter extends BaseAdapter {
        Context context;
        List<RowItem> rowItems;

        public CustomAdapter(Context context, List<RowItem> rowItems) {
            this.context = context;
            this.rowItems = rowItems;
        }

        @Override
        public int getCount() {
            return rowItems.size();
        }

        @Override
        public Object getItem(int position) {
            return rowItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return rowItems.indexOf(getItem(position));
        }

        /* private view holder class */
        private class ViewHolder {
            ImageView badge;
            TextView badgeName;
            TextView badgeInfo;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_achievement, null);
                holder = new ViewHolder();

                holder.badge = (ImageView) convertView
                        .findViewById(R.id.badges);
                holder.badgeName = (TextView) convertView
                        .findViewById(R.id.tvBadgesName);
                holder.badgeInfo = (TextView) convertView.findViewById(R.id.tvBadgesInfo);

                RowItem row_pos = rowItems.get(position);

                holder.badge.setImageResource(row_pos.getBadges_id());
                holder.badgeName.setText(row_pos.getBadgeName());
                holder.badgeInfo.setText(row_pos.getBadgeInfo());

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }
    }

}
