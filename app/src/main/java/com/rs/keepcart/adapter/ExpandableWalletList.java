package com.rs.keepcart.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.rs.keepcart.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sam on 5/7/2018.
 */

public class ExpandableWalletList extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;


    public ExpandableWalletList(Context context, List<String> listDataHeader, HashMap<String, List<String>> listDataChild) {
        this._context = context;
        this._listDataChild = listDataChild;
        this._listDataHeader = listDataHeader;
    }

    @Override
    public int getGroupCount() {
        if (_listDataHeader == null)
            return 0;
        else
            return this._listDataHeader.size();

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = String.valueOf(getGroup(groupPosition));
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.transaction_list_design, null);
        }
        TextView headerView = convertView
                .findViewById(R.id.header_title);
        headerView.setTypeface(null, Typeface.BOLD);
        headerView.setText(headerTitle);


        return convertView;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // convertView = infalInflater.inflate(R.layout.second_level_expandable_list, null);
            convertView = infalInflater.inflate(R.layout.list_item_wallet_transaction, null);
        }

        TextView listHeader = convertView.findViewById(R.id.childListItem);
        listHeader.setTypeface(null, Typeface.BOLD);
        listHeader.setText(childText);
        return convertView;

    }


    /*@Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_wallet_transaction, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }*/

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
