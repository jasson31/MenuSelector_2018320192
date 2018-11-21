package com.jasson31.menuselector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.jasson31.menuselector.Main_Select.AddRestaurant;
import static com.jasson31.menuselector.Main_Select.FindIfExist;
import static com.jasson31.menuselector.Main_Select.restaurantStringData;
import static com.jasson31.menuselector.Main_Select.restaurants;

public class Sub_Setting extends AppCompatActivity {
    public static ArrayAdapter adapter;
    private InputDialog inputDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurantStringData);
        final ListView listView = (ListView) findViewById(R.id.list_restaurants);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder clickDialog = new AlertDialog.Builder(Sub_Setting.this);
                final int itemPosition = position;
                clickDialog.setMessage("Action");
                clickDialog.setPositiveButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputDialog = new InputDialog(Sub_Setting.this, "Modify restaurant information",
                                "Type a new information.",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String name = inputDialog.GetName();
                                        int preference = inputDialog.GetPreference();
                                        if(preference == -1 || preference < 1 || preference > 5){
                                            Toast.makeText(Sub_Setting.this,
                                                    "Invalid preference\nPreference should be integer from 1 to 5",
                                                    Toast.LENGTH_SHORT).show();
                                            inputDialog.ClearInput();
                                        }
                                        else if(FindIfExist(name) && !restaurants.get(itemPosition).getName().equals(name)){
                                            Toast.makeText(Sub_Setting.this, "Restaurants already exists.",
                                                    Toast.LENGTH_SHORT).show();
                                            inputDialog.ClearInput();
                                        }
                                        else{
                                            restaurants.get(itemPosition).setName(name);
                                            restaurants.get(itemPosition).setPreference(preference);
                                            Main_Select.UpdateStringData();
                                            adapter.notifyDataSetChanged();
                                            inputDialog.dismiss();
                                        }
                                    }
                                },
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        inputDialog.dismiss();
                                    }
                                });
                        inputDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        inputDialog.setCancelable(true);
                        inputDialog.getWindow().setGravity(Gravity.CENTER);
                        inputDialog.show();
                    }
                });
                clickDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder checkDialog = new AlertDialog.Builder(Sub_Setting.this);
                        checkDialog.setTitle("Are you sure?");
                        checkDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                restaurants.remove(itemPosition);
                                restaurantStringData.remove(itemPosition);
                                Main_Select.UpdateStringData();
                                listView.clearChoices();
                                adapter.notifyDataSetChanged();
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
                });
                clickDialog.setCancelable(true);
                clickDialog.show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
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
                inputDialog = new InputDialog(Sub_Setting.this, "New restaurant",
                        "Type a new restaurant's information.",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String name = inputDialog.GetName();
                                int preference = inputDialog.GetPreference();
                                if(preference == -1 || preference < 1 || preference > 5){
                                    Toast.makeText(Sub_Setting.this,
                                            "Invalid preference\nPreference should be integer from 1 to 5",
                                            Toast.LENGTH_SHORT).show();
                                    inputDialog.ClearInput();
                                }
                                else if(FindIfExist(name)){
                                    Toast.makeText(Sub_Setting.this, "Restaurants already exists.",
                                            Toast.LENGTH_SHORT).show();
                                    inputDialog.ClearInput();
                                }
                                else{
                                    AddRestaurant(new Restaurant(name, preference));
                                    adapter.notifyDataSetChanged();
                                    inputDialog.dismiss();
                                }
                            }
                        },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                inputDialog.dismiss();
                            }
                        });
                inputDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                inputDialog.setCancelable(true);
                inputDialog.getWindow().setGravity(Gravity.CENTER);
                inputDialog.show();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
