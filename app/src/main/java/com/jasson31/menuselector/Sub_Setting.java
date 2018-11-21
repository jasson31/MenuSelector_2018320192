package com.jasson31.menuselector;

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

public class Sub_Setting extends AppCompatActivity {
    protected ArrayAdapter adapter;
    private InputDialog inputDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurantStringData);
        final ListView listView = (ListView) findViewById(R.id.ListViewTest);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Sub_Setting.this, (String)parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
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
