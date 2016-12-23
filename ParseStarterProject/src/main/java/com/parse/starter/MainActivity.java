/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    EditText userName;
    EditText passWord;
    TextView signorlogintext;
    Button loginButton;
    RelativeLayout relativeLayout;

    Boolean signUpMode;
    Boolean loggedinStatus;


    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

        signUpMode = false;
        loggedinStatus = false;

        signorlogintext = (TextView) findViewById(R.id.signOrLoginTextView);
        loginButton = (Button) findViewById(R.id.loginButton);
        userName = (EditText) findViewById(R.id.userNameText);
        passWord = (EditText) findViewById(R.id.passwordText);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        signorlogintext.setOnClickListener(this);
        userName.setOnKeyListener(this);
        passWord.setOnKeyListener(this);
        relativeLayout.setOnClickListener(this);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.signOrLoginTextView) {
            if (signUpMode == true) {
                signUpMode = false;
                loginButton.setText("LOG IN");
                signorlogintext.setText("Sign Up");
            } else {
                signUpMode = true;
                loginButton.setText("SIGN UP");
                signorlogintext.setText("Log in");
            }
        }else if(view.getId() == R.id.relativeLayout){

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_exit) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exit")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  public void signOrLogFunc(View view){

      if(signUpMode) {
          signUpMode = false;
          ParseUser user = new ParseUser();
          user.setUsername(String.valueOf(userName.getText()));
          user.setPassword(String.valueOf(passWord.getText()));

          user.signUpInBackground(new SignUpCallback() {
              @Override
              public void done(ParseException e) {
                  if (e == null) {
                      Toast.makeText(getApplicationContext(), "Sign up: Successful", Toast.LENGTH_SHORT).show();
                      ParseUser.logInInBackground(String.valueOf(userName.getText()), String.valueOf(passWord.getText()), new LogInCallback() {
                          @Override
                          public void done(ParseUser user, ParseException e) {
                              if (user != null) {
                                  loggedinStatus = true;
                                  Intent intent = new Intent(MainActivity.this, Dashboard.class);
                                  startActivityForResult(intent, 1);
                                  finish();
                              } else {
                                  Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                  } else {
                      Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_SHORT).show();
                  }
              }
          });
      }else {
          if (loggedinStatus == false) {
              ParseUser.logInInBackground(String.valueOf(userName.getText()), String.valueOf(passWord.getText()), new LogInCallback() {
                  @Override
                  public void done(ParseUser user, ParseException e) {
                      if (user != null) {
                          loggedinStatus = true;
                          Intent intent = new Intent(MainActivity.this, Dashboard.class);
                          startActivity(intent);
                          finish();
                      } else {
                          Toast.makeText(getApplicationContext(), e.getMessage().substring(e.getMessage().indexOf(" ")), Toast.LENGTH_SHORT).show();
                      }
                  }
              });
          }
      }

  }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

        if(view.getId() == R.id.userNameText){

        }
        if(view.getId() == R.id.passwordText){
            if(keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN){
                signOrLogFunc(view);
            }
        }



        return false;
    }
}
