package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Edit_Profile extends AppCompatActivity {

    EditText userName;
    EditText company;
    EditText address;
    EditText email;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);

        userName = (EditText) findViewById(R.id.nameEditText);
        company = (EditText) findViewById(R.id.companyEditText);
        address = (EditText) findViewById(R.id.addressEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        phoneNumber = (EditText) findViewById(R.id.mobileEditText);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            Intent intent = new Intent(Edit_Profile.this, Profile.class);
            Bundle extras = new Bundle();
            extras.putString("Name", userName.getText().toString());
            //extras.putString("Company", company.getText().toString());
            extras.putString("Address", address.getText().toString());
            extras.putString("Email", email.getText().toString());
            extras.putString("Phone", phoneNumber.getText().toString());
            intent.putExtras(extras);
            setResult(RESULT_OK, intent);
            this.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
