package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class Request extends AppCompatActivity {

    EditText type;
    EditText weight;
    EditText deliverTo;
    EditText destination;
    EditText notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        type = (EditText) findViewById(R.id.typeEditText);
        weight = (EditText) findViewById(R.id.weightEditText);
        deliverTo = (EditText) findViewById(R.id.deliverToEditText);
        destination = (EditText) findViewById(R.id.destinationEditText);
        notes = (EditText) findViewById(R.id.noteEditText);
    }

    public void okFunc(View view){

        ParseObject request = new ParseObject(ParseUser.getCurrentUser().getUsername() + "Request");

        request.put("Type", type.getText().toString());
        request.put("Weight", weight.getText().toString());
        request.put("DeliverTo", deliverTo.getText().toString());
        request.put("Destination", destination.getText().toString());
        request.put("Notes", notes.getText().toString());

        request.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
             if(e == null){
                 Toast.makeText(getApplicationContext(), "Request submitted successfully", Toast.LENGTH_SHORT).show();
             }else{
                 e.printStackTrace();
             }

            }
        });

        Intent intent = new Intent(Request.this, Dashboard.class);
        setResult(RESULT_OK, intent);
        this.onBackPressed();
    }

    public void cancelFunc(View view){
        this.onBackPressed();
        finish();
    }
}
