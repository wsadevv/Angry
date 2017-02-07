package com.wsadevv.angry;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView forecastList;
    Button refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillList();
        refresh = (Button) findViewById(R.id.button_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FetchWeatherTask task = new FetchWeatherTask();
                task.execute();

            }
        });


    }

    public void fillList(){
        String[] forecasts = {"Sunny","Clouds","Hot", "Really Hot","Burning"};
        forecastList = (ListView) findViewById(R.id.forecast_list);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,forecasts);
        forecastList.setAdapter(myAdapter);




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            FetchWeatherTask task = new FetchWeatherTask();
            task.execute();
            Toast.makeText(this, "Chamou a task", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
