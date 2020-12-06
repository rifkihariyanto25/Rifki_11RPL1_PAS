package com.example.rifki_11rpl1_29;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    EditText etUser;
    EditText etPass;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("login", MODE_PRIVATE);
        getSupportActionBar().hide();
        etUser = (EditText)findViewById(R.id.etUser);
        etPass = (EditText)findViewById(R.id.etPass);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etUser.getText().toString().equalsIgnoreCase("admin")
                        && etPass.getText().toString().equalsIgnoreCase("admin")){
                    //saving ke SP
                    editor = pref.edit();
                    editor.putString("username", etUser.getText().toString());
                    editor.putString("status", "login");
                    editor.apply();
                    //menuju ke main menu
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                    finish();
                }
            }
        });
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Login.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present. getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
}