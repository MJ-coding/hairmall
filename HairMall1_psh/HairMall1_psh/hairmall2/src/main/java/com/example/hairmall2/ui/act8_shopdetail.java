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
        //리뷰작성버튼
        Button button =(Button)findViewById(R.id.write_review);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),act10_review.class);
                //act6_shopsearching에서 설정해준 이름data 가져오기
                String shop_name = getIntent().getStringExtra("shop_name");
                String gpa =getIntent().getStringExtra("gpa");
                //다시 다음 activity로 넘기기위해 intent data입력하기
                intent.putExtra("shop_name",shop_name);
                intent.putExtra("gpa",gpa);
                startActivity(intent);

            }
        });
    }
}
