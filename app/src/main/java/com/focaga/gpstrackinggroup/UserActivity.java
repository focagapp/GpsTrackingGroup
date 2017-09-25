package com.focaga.gpstrackinggroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class UserActivity extends AppCompatActivity {
    Button avanti;
    EditText utente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        avanti = (Button) findViewById(R.id.button2);
        utente = (EditText) findViewById(R.id.editText);

        avanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    FileOutputStream fos = new FileOutputStream(MapsActivity.fileSave);
                    fos.write(utente.getText().toString().getBytes());
                    fos.close();
                    Intent mapsActivity = new Intent(UserActivity.this, MapsActivity.class);
                    startActivity(mapsActivity);
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });
    }
}
