package com.example.hairmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hairmall2.R;

public class act8_shopdetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act8_shopdetail);

        Button button =(Button)findViewById(R.id.write_review);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),act10_review.class);

                String shop_name = getIntent().getStringExtra("shop_name");
                intent.putExtra("shop_name",shop_name);
                startActivity(intent);

            }
        });
    }

}
