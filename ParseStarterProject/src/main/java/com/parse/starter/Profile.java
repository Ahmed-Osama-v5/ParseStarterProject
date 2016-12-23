package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    TextView userName;
    TextView company;
    TextView address;
    TextView email;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);

        userName = (TextView) findViewById(R.id.userTextView);
        company = (TextView) findViewById(R.id.companyEditText);
        address = (TextView) findViewById(R.id.addressTextView);
        email = (TextView) findViewById(R.id.emailTextView);
        phone = (TextView) findViewById(R.id.mobileTextView);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.edit_profile) {
            Intent intent = new Intent(Profile.this, Edit_Profile.class);
            startActivityForResult(intent, 1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                Bundle extras = data.getExtras();

                String name = extras.getString("Name");
                //String company_name = extras.getString("Company");
                String address_text = extras.getString("Address");
                String email_text = extras.getString("Email");
                String phone_text = extras.getString("Phone");

                userName.setText(name);
                //company.setText(company_name);
                address.setText(address_text);
                email.setText(email_text);
                phone.setText(phone_text);
            }
        }
    }
}
