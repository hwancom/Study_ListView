package com.example.administrator.study_listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {

    // Adapter에 추가된 data를 저장하기 위한 ArrayList.
    private ArrayList<ListItem> allItemList = new ArrayList<>();

    // 필터링된 결과 data를 저장하기 위한 ArrayList. 최초에는 전체 리스트 보유.
    private ArrayList<ListItem> filteredItemList = allItemList;

    Filter listFilter;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 data의 개수를 리턴.
    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    // 지정한 위치(position)에 있는 data 리턴.
    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    // 지정한 위치(position)에 있는 data와 관계된 아이템(row)의 ID를 리턴.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 data를 화면에 출력하는데 사용될 View를 리턴.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // final int pos = position;
        final Context context = viewGroup.getContext();

        // "listview_item" Layout을 inflate하여 view 참조 획득.
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView_name = view.findViewById(R.id.textView_name);
        TextView textView_mobile = view.findViewById(R.id.textView_mobile);

        // Data Set(filteredItemList)에서 position에 위치한 data 참조 획득.
        ListItem listItem = filteredItemList.get(position);

        // 아이템 내 각 위젯에 data 반영.
        imageView.setImageDrawable(listItem.getIcon());
        textView_name.setText(listItem.getName());
        textView_mobile.setText(listItem.getMobile());

        return view;
    }

    // Filterable 인터페이스를 implements 하였으므로, getFilter() 함수를 override
    @Override
    public Filter getFilter() {

        if (listFilter == null) {
            listFilter = new ListFilter();
        }

        return listFilter;
    }

    // Item data 추가를 위한 함수.
    public void addItem(Drawable icon, String name, String mobile) {
        ListItem item = new ListItem();

        item.setIcon(icon);
        item.setName(name);
        item.setMobile(mobile);

        allItemList.add(item);
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0) {
                results.values = allItemList;
                results.count = allItemList.size();
            } else {
                ArrayList<ListItem> itemList = new ArrayList<>();

                for (ListItem item : allItemList) {

                    // textView_name, textView_mobile 둘 다 Filtering 수행.
                    if (item.getName().toUpperCase().contains(charSequence.toString().toUpperCase()) ||
                            item.getMobile().toUpperCase().contains(charSequence.toString().toUpperCase())) {
                        itemList.add(item);
                    }
                }

                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            // update listview by filtered data list.
            filteredItemList = (ArrayList<ListItem>) filterResults.values;

            // notify
            if (filterResults.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
