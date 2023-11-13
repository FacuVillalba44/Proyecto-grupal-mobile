package com.fj.developferjuarez;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_mascota extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);
        Button btnVolverveter = findViewById(R.id:btnVolverveter);

        btnVolverveter.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(activity_mascota.this, activity_mascota.class );
            startActivity(intent);
        });

    }
}