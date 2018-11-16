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
    public static final ArrayList<Restaurant> testRestaurant = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);

        testRestaurant.add(new Restaurant("A", 2));
        testRestaurant.add(new Restaurant("B", 3));
        testRestaurant.add(new Restaurant("C", 1));
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

    public void Randomizer(){
        TextView result = (TextView) findViewById(R.id.text_result);
        int randomIndex = new Random().nextInt(3);
        result.setText(testRestaurant.get(randomIndex).getName());
        for(int i = 0; i < testRestaurant.size(); i++){
            if(i != randomIndex)
                continue;
            testRestaurant.get(randomIndex).setPreference(testRestaurant.get(randomIndex).getPreference() + 1);
        }
        //Toast.makeText(this, testRestaurant.get(randomIndex).getPreference(), Toast.LENGTH_LONG).show();
    }
}
