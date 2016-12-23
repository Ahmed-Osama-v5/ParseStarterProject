package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.parse.ParseUser;

public class Dashboard extends AppCompatActivity {

    ImageView packageImage;
    ImageView packageBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void trainScheduleFunc(View view){
     Intent intent = new Intent(Dashboard.this, Train_Schedule.class);
        startActivity(intent);
    }

    public void transitFunc(View view){
        Intent intent = new Intent(Dashboard.this, Transit.class);
        startActivity(intent);
    }

    public void deliveringFunc(View view){
        Intent intent = new Intent(Dashboard.this, Delivering.class);
        startActivity(intent);
    }

    public void deliveredFunc(View view){
        Intent intent = new Intent(Dashboard.this, Delivered.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);

        packageImage = (ImageView) findViewById(R.id.packageImageView);
        packageBackground = (ImageView) findViewById(R.id.packBackImageView);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Intent intent = new Intent(Dashboard.this, Profile.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_logout) {

            ParseUser.logOut();

            Intent intent = new Intent(Dashboard.this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void packageImageClickFunc(View view){
        Intent intent = new Intent(Dashboard.this, Request.class);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                packageBackground.setImageResource(R.drawable.fdal_53);
            }
        }
    }
}
