package com.example.hairmall2.ui.slideshow;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hairmall2.R;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class act11_stylesearching extends  Fragment implements View.OnClickListener {

    Button puthairstyle;
    Button Btn1;
    Button Btn2;
    Button Btn3;
    Button Btn4;
    Button Btn5;
    Button Btn6;
    Button genman;
    ImageView Img1;
    ImageView Img2;
    ImageView Img3;
    ImageView Img4;
    ImageView Img5;
    ImageView Img6;

    Button Btnw1;
    Button Btnw2;
    Button Btnw3;
    Button Btnw4;
    Button Btnw5;
    Button Btnw6;
    Button genwoman;

    ImageView Imgw1;
    ImageView Imgw2;
    ImageView Imgw3;
    ImageView Imgw4;
    ImageView Imgw5;
    ImageView Imgw6;


    private act11_stylesearchingviewmodel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(act11_stylesearchingviewmodel.class);
        View root = inflater.inflate(R.layout.act11_stylesearchingm, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        puthairstyle = root.findViewById(R.id.puthairstyle);
        Btn1 = root.findViewById(R.id.Button1);
        Btn2 = root.findViewById(R.id.Button2);
        Btn3 = root.findViewById(R.id.Button3);
        Btn4 = root.findViewById(R.id.Button4);
        Btn5 = root.findViewById(R.id.Button5);
        Btn6 = root.findViewById(R.id.Button6);
        Img1 = root.findViewById(R.id.ImgSet1);
        Img2 = root.findViewById(R.id.ImgSet2);
        Img3 = root.findViewById(R.id.ImgSet3);
        Img4 = root.findViewById(R.id.ImgSet4);
        Img5 = root.findViewById(R.id.ImgSet5);
        Img6 = root.findViewById(R.id.ImgSet6);
        genman = root.findViewById(R.id.genman);

        Btnw1 = root.findViewById(R.id.Buttonw1);
        Btnw2 = root.findViewById(R.id.Buttonw2);
        Btnw3 = root.findViewById(R.id.Buttonw3);
        Btnw4 = root.findViewById(R.id.Buttonw4);
        Btnw5 = root.findViewById(R.id.Buttonw5);
        Btnw6 = root.findViewById(R.id.Buttonw6);
        Imgw1 = root.findViewById(R.id.ImgSetw1);
        Imgw2 = root.findViewById(R.id.ImgSetw2);
        Imgw3 = root.findViewById(R.id.ImgSetw3);
        Imgw4 = root.findViewById(R.id.ImgSetw4);
        Imgw5 = root.findViewById(R.id.ImgSetw5);
        Imgw6 = root.findViewById(R.id.ImgSetw6);
        genwoman = root.findViewById(R.id.genwoman);


        Btn1.setOnClickListener(this);
        Btn2.setOnClickListener(this);
        Btn3.setOnClickListener(this);
        Btn4.setOnClickListener(this);
        Btn5.setOnClickListener(this);
        Btn6.setOnClickListener(this);
        genman.setOnClickListener(this);

        Btnw1.setOnClickListener(this);
        Btnw2.setOnClickListener(this);
        Btnw3.setOnClickListener(this);
        Btnw4.setOnClickListener(this);
        Btnw5.setOnClickListener(this);
        Btnw6.setOnClickListener(this);
        genwoman.setOnClickListener(this);


        puthairstyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hairintent = new Intent();
                ComponentName haircomponentName = new ComponentName(
                        "com.example.hairmall2",
                        "com.example.hairmall2.puthairstyles");

                hairintent.setComponent(haircomponentName);
                startActivity(hairintent);


            }
        });

        return root;
    }


    @Override
    public void onClick(View view) {

        if (view == genwoman) {
            Btn1.setVisibility(GONE);
            Btn2.setVisibility(GONE);
            Btn3.setVisibility(GONE);
            Btn4.setVisibility(GONE);
            Btn5.setVisibility(GONE);
            Btn6.setVisibility(GONE);
            Imgw1.setVisibility(View.VISIBLE);

            Btnw1.setVisibility(VISIBLE);
            Btnw2.setVisibility(VISIBLE);
            Btnw3.setVisibility(VISIBLE);
            Btnw4.setVisibility(VISIBLE);
            Btnw5.setVisibility(VISIBLE);
            Btnw6.setVisibility(VISIBLE);

            Img1.setVisibility(View.GONE);
            Img2.setVisibility(View.GONE);
            Img3.setVisibility(View.GONE);
            Img4.setVisibility(View.GONE);
            Img5.setVisibility(View.GONE);
            Img6.setVisibility(View.GONE);
        }

        if (view == genman) {
            Btnw1.setVisibility(GONE);
            Btnw2.setVisibility(GONE);
            Btnw3.setVisibility(GONE);
            Btnw4.setVisibility(GONE);
            Btnw5.setVisibility(GONE);
            Btnw6.setVisibility(GONE);
            Img1.setVisibility(View.VISIBLE);

            Btn1.setVisibility(VISIBLE);
            Btn2.setVisibility(VISIBLE);
            Btn3.setVisibility(VISIBLE);
            Btn4.setVisibility(VISIBLE);
            Btn5.setVisibility(VISIBLE);
            Btn6.setVisibility(VISIBLE);

            Imgw1.setVisibility(View.GONE);
            Imgw2.setVisibility(View.GONE);
            Imgw3.setVisibility(View.GONE);
            Imgw4.setVisibility(View.GONE);
            Imgw5.setVisibility(View.GONE);
            Imgw6.setVisibility(View.GONE);
        }


        if (view == Btn1) {
            Img1.setVisibility(VISIBLE);
            Img2.setVisibility(View.GONE);
            Img3.setVisibility(View.GONE);
            Img4.setVisibility(View.GONE);
            Img5.setVisibility(View.GONE);
            Img6.setVisibility(View.GONE);
        }
        if (view == Btn2) {
            Img2.setVisibility(VISIBLE);
            Img1.setVisibility(View.GONE);
            Img3.setVisibility(View.GONE);
            Img4.setVisibility(View.GONE);
            Img5.setVisibility(View.GONE);
            Img6.setVisibility(View.GONE);
        }
        if (view == Btn3) {
            Img3.setVisibility(VISIBLE);
            Img2.setVisibility(View.GONE);
            Img1.setVisibility(View.GONE);
            Img4.setVisibility(View.GONE);
            Img5.setVisibility(View.GONE);
            Img6.setVisibility(View.GONE);
        }
        if (view == Btn4) {
            Img4.setVisibility(VISIBLE);
            Img2.setVisibility(View.GONE);
            Img3.setVisibility(View.GONE);
            Img1.setVisibility(View.GONE);
            Img5.setVisibility(View.GONE);
            Img6.setVisibility(View.GONE);
        }
        if (view == Btn5) {
            Img5.setVisibility(VISIBLE);
            Img2.setVisibility(View.GONE);
            Img3.setVisibility(View.GONE);
            Img4.setVisibility(View.GONE);
            Img1.setVisibility(View.GONE);
            Img6.setVisibility(View.GONE);
        }
        if (view == Btn6) {
            Img6.setVisibility(VISIBLE);
            Img2.setVisibility(View.GONE);
            Img3.setVisibility(View.GONE);
            Img4.setVisibility(View.GONE);
            Img5.setVisibility(View.GONE);
            Img1.setVisibility(View.GONE);
        }


        if (view == Btnw1) {
            Imgw1.setVisibility(VISIBLE);
            Imgw2.setVisibility(View.GONE);
            Imgw3.setVisibility(View.GONE);
            Imgw4.setVisibility(View.GONE);
            Imgw5.setVisibility(View.GONE);
            Imgw6.setVisibility(View.GONE);
        }
        if (view == Btnw2) {
            Imgw2.setVisibility(VISIBLE);
            Imgw1.setVisibility(View.GONE);
            Imgw3.setVisibility(View.GONE);
            Imgw4.setVisibility(View.GONE);
            Imgw5.setVisibility(View.GONE);
            Imgw6.setVisibility(View.GONE);
        }
        if (view == Btnw3) {
            Imgw3.setVisibility(VISIBLE);
            Imgw2.setVisibility(View.GONE);
            Imgw1.setVisibility(View.GONE);
            Imgw4.setVisibility(View.GONE);
            Imgw5.setVisibility(View.GONE);
            Imgw6.setVisibility(View.GONE);
        }
        if (view == Btnw4) {
            Imgw4.setVisibility(VISIBLE);
            Imgw2.setVisibility(View.GONE);
            Imgw3.setVisibility(View.GONE);
            Imgw1.setVisibility(View.GONE);
            Imgw5.setVisibility(View.GONE);
            Imgw6.setVisibility(View.GONE);
        }
        if (view == Btnw5) {
            Imgw5.setVisibility(VISIBLE);
            Imgw2.setVisibility(View.GONE);
            Imgw3.setVisibility(View.GONE);
            Imgw4.setVisibility(View.GONE);
            Imgw1.setVisibility(View.GONE);
            Imgw6.setVisibility(View.GONE);
        }
        if (view == Btnw6) {
            Imgw6.setVisibility(VISIBLE);
            Imgw2.setVisibility(View.GONE);
            Imgw3.setVisibility(View.GONE);
            Imgw4.setVisibility(View.GONE);
            Imgw5.setVisibility(View.GONE);
            Imgw1.setVisibility(View.GONE);
        }
    }


}





