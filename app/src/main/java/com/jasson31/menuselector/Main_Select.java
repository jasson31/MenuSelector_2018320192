package com.jasson31.menuselector;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
                RandomizerBtnAction();
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
                Intent intent = new Intent(Main_Select.this, Sub_Setting.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public static void AddRestaurant(Restaurant r){
        restaurants.add(r);
        GetProbabilitySum();
        int i = restaurants.size() - 1;
        restaurantStringData.add(GetPrintableText(i));
        for(i = 0; i < restaurants.size() - 1; i++){
            restaurantStringData.set(i, GetPrintableText(i));
        }
    }
    public static boolean FindIfExist(String name){
        for(int i = 0; i < restaurants.size(); i++){
            if (restaurants.get(i).getName().equals(name))
                return true;
        }
        return false;
    }
    public static String GetPrintableText(int index){
        return (index + 1) + ". " + restaurants.get(index).getName() + " : "
                + Math.round((float)restaurants.get(index).getProbability() / GetProbabilitySum() * 10000) / 100.0
                + " (" + restaurants.get(index).getPreference() + ")";
    }
    public void RandomizerBtnAction(){
        if(restaurants.size() < 2){
            Toast.makeText(this, "There should be more than 2 restaurants", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            AlertDialog.Builder checkDialog = new AlertDialog.Builder(this);
            checkDialog.setTitle("Randomizer");
            checkDialog.setMessage("Are you ready?");
            checkDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Randomizer();
                    dialog.dismiss();
                }
            });
            checkDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            checkDialog.setCancelable(true);
            checkDialog.show();
        }
    }
    public void Randomizer(){
        TextView result = (TextView) findViewById(R.id.text_result);
        int random = new Random().nextInt(GetProbabilitySum());
        int index = 0;
        while(random > 0){
            random -= restaurants.get(index).getProbability();
            if(random <= 0){
                result.setText(restaurants.get(index).getName());
                restaurants.get(index).setProbability(0);
                for(int i = 0; i < restaurants.size(); i++){
                    if(i != index){
                        restaurants.get(i).setProbability(restaurants.get(i).getProbability()
                                + restaurants.get(i).getPreference());
                    }
                }
            }
            index++;
        }
        for(int i = 0; i < restaurants.size(); i++){
            restaurantStringData.set(i, GetPrintableText(i));
        }
    }
    public static int GetProbabilitySum(){
        int probabilitySum = 0;
        for(int i = 0; i < restaurants.size(); i++){
            probabilitySum += restaurants.get(i).getProbability();
        }
        return probabilitySum;
    }
}
