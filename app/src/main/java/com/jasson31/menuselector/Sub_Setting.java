package com.jasson31.menuselector;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Sub_Setting extends AppCompatActivity {
    static final ArrayList<Restaurant> testRestaurant = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        testRestaurant.add(new Restaurant("A", 2));
        testRestaurant.add(new Restaurant("B", 3));
        testRestaurant.add(new Restaurant("C", 1));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        for(int i = 0; i < testRestaurant.size(); i++){
            adapter.add((i + 1) + ". " + testRestaurant.get(i).getName() + " " + testRestaurant.get(i).getPreference());
        }
        ListView listView = (ListView) findViewById(R.id.ListViewTest);
        listView.setAdapter(adapter);
        //final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);

        /*ListView listView;
        ListViewAdapter adapter;
        adapter = new ListViewAdapter();
        listView = (ListView) findViewById(R.id.ListViewTest);
        listView.setAdapter(adapter);
        adapter.addItem("Box", "Account Box Black");
        adapter.addItem("Circle", "Account Circle Black");
        adapter.addItem("Ind", "Assignment Ind Black");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TestItem item = (TestItem) parent.getItemAtPosition(position);
                String titleStr = item.getTitle();
                String descStr = item.getDesc();
                Toast.makeText(Sub_Setting.this, titleStr + "\n" + descStr, Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
