package com.jasson31.menuselector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Main_Select extends AppCompatActivity{
    public static final ArrayList<Restaurant> restaurants = new ArrayList<>();
    public static final ArrayList<String> restaurantStringData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);

        Button randomizer = (Button) findViewById(R.id.button_randomize);
        randomizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Randomizer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_config:
                Toast.makeText(this, "to next page", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Main_Select.this, Sub_Setting.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public static void AddRestaurant(Restaurant r){
        restaurants.add(r);
        int i = restaurants.size() - 1;
        restaurantStringData.add((i + 1) + ". " + restaurants.get(i).getName() + " " + restaurants.get(i).getPreference());
    }
    public void Randomizer(){
        TextView result = (TextView) findViewById(R.id.text_result);
        if(restaurants.size() == 0)
            return;
        int randomIndex = new Random().nextInt(restaurants.size());
        result.setText(restaurants.get(randomIndex).getName());
    }
}
