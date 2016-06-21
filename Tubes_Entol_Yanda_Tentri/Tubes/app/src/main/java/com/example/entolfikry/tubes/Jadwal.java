package com.example.entolfikry.tubes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Jadwal extends AppCompatActivity implements View.OnClickListener {

    Button btncalon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        btncalon = (Button) findViewById(R.id.buttoncalon);
        btncalon.setOnClickListener(this);
    }

    @Override
    public void onClick (View view){
        if (view == btncalon){
            startActivity(new Intent(this,MenuUtama.class));
        }
    }
}