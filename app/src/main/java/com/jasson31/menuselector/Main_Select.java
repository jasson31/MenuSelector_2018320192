package com.jasson31.menuselector;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Main_Select extends AppCompatActivity{
    public static final ArrayList<Restaurant> restaurants = new ArrayList<>();
    public static final ArrayList<String> restaurantStringData = new ArrayList<>();
    private TextView result;
    private int order = 0;
    private int sleepTime = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_select);

        result = (TextView) findViewById(R.id.text_result);
        Button randomizer = (Button) findViewById(R.id.button_randomize);
        randomizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomizerBtnAction();
            }
        });
        LoadData();
    }

    @Override
    protected void onDestroy() {
        SaveData();
        super.onDestroy();
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
        restaurantStringData.add(GetPrintableText(restaurants.size() - 1));
        UpdateStringData();
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
                + "% (" + restaurants.get(index).getPreference() + ")";
    }
    public void RandomizerBtnAction(){
        if(restaurants.size() < 2){
            Toast.makeText(this, "There should be more than 2 restaurants", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            AlertDialog.Builder checkDialog = new AlertDialog.Builder(Main_Select.this);
            checkDialog.setTitle("Randomizer");
            checkDialog.setMessage("Are you ready?");
            checkDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Randomizer();
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

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            result.setText(restaurants.get(order % restaurants.size()).getName());
            order++;
            sleepTime += 5;
            if(order == 16){
                result.setTextColor(Color.BLACK);
                int random = new Random().nextInt(GetProbabilitySum());
                int index = 0;
                while(true){
                    random -= restaurants.get(index).getProbability();
                    if(random <= 0){
                        result.setText(restaurants.get(index).getName());
                        restaurants.get(index).setProbability(0);
                        for(int i = 0; i < restaurants.size(); i++)
                            if(i != index)
                                restaurants.get(i).setProbability(restaurants.get(i).getProbability()
                                        + restaurants.get(i).getPreference());
                        UpdateStringData();
                        break;
                    }
                    index++;
                }
            }
        }
    };

    public void Randomizer(){
        result.setTextColor(Color.GRAY);
        sleepTime = 100;
        Thread roulette = new Thread(){
            public void run(){
                order = new Random().nextInt(restaurants.size());
                while(order < 15){
                    try{
                        sleep(sleepTime);
                    }catch (Exception e){
                        e.printStackTrace();
                        break;
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        };
        roulette.start();
    }

    public static void UpdateStringData(){
        for(int i = 0; i < restaurants.size(); i++)
            restaurantStringData.set(i, GetPrintableText(i));
    }

    public static int GetProbabilitySum(){
        int probabilitySum = 0;
        for(int i = 0; i < restaurants.size(); i++)
            probabilitySum += restaurants.get(i).getProbability();
        return probabilitySum;
    }

    public void SaveData() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir() + "SavedData.txt", false));
            int index = 0;
            while (index < restaurants.size()) {
                bw.write(restaurants.get(index).getName() + "\n");
                bw.write(restaurants.get(index).getPreference() + "\n");
                bw.write(restaurants.get(index).getProbability() + "\n");
                index++;
            }
            bw.close();
            Toast.makeText(Main_Select.this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Main_Select.this, "Save error", Toast.LENGTH_SHORT).show();
        }
    }

    public void LoadData(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(getFilesDir() + "SavedData.txt"));
            restaurants.clear();
            restaurantStringData.clear();
            String str = null;
            while((str = br.readLine()) != null){
                AddRestaurant(new Restaurant(str, Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine())));
            }
            br.close();
        } catch(Exception e){
            e.printStackTrace();
            Toast.makeText(Main_Select.this, "Load error", Toast.LENGTH_SHORT).show();
        }
    }
}
