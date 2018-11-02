package com.jasson31.menuselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

class Restaurant {
    public String name;
    public int preference;
    public int probability;
}

public class Sub_Setting extends AppCompatActivity {

    public Restaurant[] menuList = new Restaurant[100];
    public int menuCount = 0;
    public Restaurant testmenu = new Restaurant();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView scroll = (TextView) findViewById(R.id.menu_list);
        scroll.setMovementMethod(ScrollingMovementMethod.getInstance());

        for(int i = 0; i < menuList.length; i++){
            menuList[i] = new Restaurant();
        }
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

    protected void PrintMenu(View v){
        TextView menuListText = (TextView) findViewById(R.id.menu_list);
        menuListText.setText(testmenu.name + " " + testmenu.preference + "\n");
    }

    protected void AddMenu(View v){
        TextView name = (TextView) findViewById(R.id.input_name);
        TextView preference = (TextView) findViewById(R.id.input_preference);
        testmenu.name = name.getText().toString();
        testmenu.preference = Integer.parseInt(preference.getText().toString());

        menuCount++;
        PrintMenu(v);
        name.setText("");
        preference.setText("");
    }

    protected void DeleteMenu(View v){
        TextView name = (TextView) findViewById(R.id.input_name);
        TextView preference = (TextView) findViewById(R.id.input_preference);
        TextView menuList = (TextView) findViewById(R.id.menu_list);
        menuList.append(name.getText().toString() + preference.getText().toString());
    }

    protected void ResetMenu(View v){
        TextView name = (TextView) findViewById(R.id.input_name);
        TextView preference = (TextView) findViewById(R.id.input_preference);
        TextView menuList = (TextView) findViewById(R.id.menu_list);
        menuList.setText("");
        menuCount = 0;
    }
}
