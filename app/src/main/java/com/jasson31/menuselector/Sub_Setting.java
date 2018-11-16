package com.jasson31.menuselector;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Sub_Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //CreateListView();
        /*Main_Select.testRestaurant.add(new Restaurant("A", 2));
        Main_Select.testRestaurant.add(new Restaurant("B", 3));
        Main_Select.testRestaurant.add(new Restaurant("C", 1));

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        for(int i = 0; i < Main_Select.testRestaurant.size(); i++){
            adapter.add((i + 1) + ". " + Main_Select.testRestaurant.get(i).getName() + " " + Main_Select.testRestaurant.get(i).getPreference());
        }
        final ListView listView = (ListView) findViewById(R.id.ListViewTest);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Sub_Setting.this, (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });*/
        CreateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_add:
                AlertDialog.Builder ad = new AlertDialog.Builder(Sub_Setting.this);
                ad.setTitle("Title");
                ad.setMessage("Message");
                final EditText et = new EditText(Sub_Setting.this);
                ad.setView(et);
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Sub_Setting.this, et.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void CreateListView(){
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        for(int i = 0; i < Main_Select.testRestaurant.size(); i++){
            adapter.add((i + 1) + ". " + Main_Select.testRestaurant.get(i).getName() + " " + Main_Select.testRestaurant.get(i).getPreference());
        }
        final ListView listView = (ListView) findViewById(R.id.ListViewTest);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Sub_Setting.this, (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
