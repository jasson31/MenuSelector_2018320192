package com.jasson31.menuselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static com.jasson31.menuselector.Main_Select.AddRestaurant;
import static com.jasson31.menuselector.Main_Select.restaurantStringData;

public class Sub_Setting extends AppCompatActivity {
    protected ArrayAdapter adapter;
    private InputDialog inputDialog;

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
                        "Type a new restaurant's information.", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = inputDialog.GetName();
                        int preference = inputDialog.GetPreference();
                        AddRestaurant(new Restaurant(name, preference));
                        adapter.notifyDataSetChanged();
                        inputDialog.dismiss();
                    }
                },
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                inputDialog.dismiss();
                            }
                        });


                /*inputDialog.setTitle("Add");
                inputDialog.setMessage("Type a new restaurant information.");
                final EditText restaurantName = new EditText(Sub_Setting.this);
                inputDialog.setView(restaurantName);
                inputDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AddRestaurant(new Restaurant(restaurantName.getText().toString(), 2));
                        adapter.notifyDataSetChanged();
                    }
                });
                inputDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });*/

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
