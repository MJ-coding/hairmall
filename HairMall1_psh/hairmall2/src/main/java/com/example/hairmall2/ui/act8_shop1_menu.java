package com.example.hairmall2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hairmall2.R;

public class act8_shop1_menu extends AppCompatActivity {

    int [] ImageId = { R.drawable.xino_menu2, R.drawable.xino_menu3, R.drawable.xino_menu4, R.drawable.xino_menu1};
    ImageView menu_imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act8_menu1);

        menu_imageview = (ImageView) menu_imageview.findViewById(R.id.shop1_menu1);

        // res/drawable 폴더에 있는 이미지로 셋팅하기
        menu_imageview.setImageResource(R.drawable.xino_menu1);

        menu_imageview.setOnClickListener(new MyListener());

    } // end onCreate


    class MyListener implements View.OnClickListener {

        int i = 0;
        int length = ImageId.length;

        @Override
        public void onClick(View v) {
            menu_imageview.setImageResource(ImageId[i]);

            i+=1;
            if(i == ImageId.length) i = 0;
        } // end onClick


    } // end MyListener()

} //end Class


