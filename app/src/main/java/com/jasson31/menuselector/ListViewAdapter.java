/*package com.jasson31.menuselector;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<TestItem> listViewItemList = new ArrayList<TestItem>();
    public ListViewAdapter(){

    }
    @Override
    public int getCount(){
        return listViewItemList.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final int pos = position;
        final Context context = parent.getContext();
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.test_item, parent, false);
        }
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1);
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView2);
        TestItem testItem = listViewItemList.get(position);
        titleTextView.setText(testItem.getTitle());
        descTextView.setText(testItem.getDesc());
        return convertView;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public Object getItem(int position){
        return listViewItemList.get(position);
    }
    public void addItem(String title, String desc){
        TestItem item = new TestItem();
        item.setTitle(title);
        item.setDesc(desc);
        listViewItemList.add(item);
    }
}*/