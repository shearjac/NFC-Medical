package com.example.nfcmedical;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class CategoryAdapter extends BaseExpandableListAdapter {
    ArrayList<String> profileHeadings;
    HashMap<String, ArrayList<String>> categoryResults;

    public CategoryAdapter(ArrayList<String> profileHeadings, HashMap<String, ArrayList<String>> categoryResults) {
        this.profileHeadings = profileHeadings;
        this.categoryResults = categoryResults;
    }

    @Override
    public int getGroupCount() {
        return profileHeadings.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return categoryResults.get(profileHeadings.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return profileHeadings.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoryResults.get(profileHeadings.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        TextView categoryName = convertView.findViewById(android.R.id.text1);
        String name = String.valueOf(getGroup(groupPosition));
        categoryName.setText(name);
        categoryName.setTypeface(null, Typeface.BOLD);
        categoryName.setTextSize(24);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_selectable_list_item, parent, false);
        TextView categoryResults = convertView.findViewById(android.R.id.text1);
        String data = String.valueOf(getChild(groupPosition, childPosition));
        categoryResults.setText(data);
        categoryResults.setTextSize(18);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
