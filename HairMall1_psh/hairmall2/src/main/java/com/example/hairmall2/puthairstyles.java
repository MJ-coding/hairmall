package com.example.hairmall2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.gesture.Gesture;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;

public class puthairstyles extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener//, GestureDetector.OnGestureListener
{

    private ImageView trigger;
    private ImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    private float mScale = 1f;
    private ScaleGestureDetector mScaleDetector;
    GestureDetector gestureDetector;

    final static float move =200;
    float ratio = 1.0f;
    int baseDist;
    float baseRatio;
    int testratio = 2;
    private ImageView hairs11, hairs22, hairs33, hairs44;
    private ImageView hairsw11, hairsw22, hairsw33, hairsw44;
    protected boolean gender = true; // man, default
    //protected boolean dummy=true;

    private ImageButton hair1layout, hair2layout, hair3layout, hair4layout;
    private ImageButton hairw1layout, hairw2layout, hairw3layout, hairw4layout;
    private ImageButton wc,mc;

    int XX;
    int YY;


    private ViewGroup rootLayout;
    private int _xDelta, _yDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puthairstyles);
        rootLayout = (ViewGroup)findViewById(R.id.view_root2);



        ProfileImage = (ImageView) findViewById(R.id.Profile_Image2);
        trigger = (ImageView) findViewById(R.id.trigger2);

        trigger.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
            }
        });

        //samimg = (ImageView) rootLayout.findViewById(R.id.sampleimageView);
//man moving hairs
        hairs11 = (ImageView) rootLayout.findViewById(R.id.hairs1id2);
        hairs22 = (ImageView) rootLayout.findViewById(R.id.hairs2id2);
        hairs33 = (ImageView) rootLayout.findViewById(R.id.hairs3id2);
        hairs44 = (ImageView) rootLayout.findViewById(R.id.hairs4id2);
        wc = (ImageButton) rootLayout.findViewById(R.id.wc2);
        mc = (ImageButton) rootLayout.findViewById(R.id.mc2);

//woman moving hairs

        hairsw11 = (ImageView) rootLayout.findViewById(R.id.hairsw1id2);
        hairsw22 = (ImageView) rootLayout.findViewById(R.id.hairsw2id2);
        hairsw33 = (ImageView) rootLayout.findViewById(R.id.hairsw3id2);
        hairsw44 = (ImageView) rootLayout.findViewById(R.id.hairsw4id2);

        //hairs11.setTextsize(ratio+15)
// man hair layout

        hair1layout=(ImageButton)rootLayout.findViewById(R.id.hair1layout2);
        hair2layout=(ImageButton)rootLayout.findViewById(R.id.hair2layout2);
        hair3layout=(ImageButton)rootLayout.findViewById(R.id.hair3layout2);
        hair4layout=(ImageButton)rootLayout.findViewById(R.id.hair4layout2);
//woman hair layout

        hairw1layout=(ImageButton)rootLayout.findViewById(R.id.hairw1layout2);
        hairw2layout=(ImageButton)rootLayout.findViewById(R.id.hairw2layout2);
        hairw3layout=(ImageButton)rootLayout.findViewById(R.id.hairw3layout2);
        hairw4layout=(ImageButton)rootLayout.findViewById(R.id.hairw4layout2);

        hairs11.setOnClickListener(this);
        hairs22.setOnClickListener(this);
        hairs33.setOnClickListener(this);
        hairs44.setOnClickListener(this);

        hairsw11.setOnClickListener(this);
        hairsw22.setOnClickListener(this);
        hairsw33.setOnClickListener(this);
        hairsw44.setOnClickListener(this);

        wc.setOnClickListener(this);
        mc.setOnClickListener(this);

        hair1layout.setOnClickListener(this);
        hair2layout.setOnClickListener(this);
        hair3layout.setOnClickListener(this);
        hair4layout.setOnClickListener(this);

        hairw1layout.setOnClickListener(this);
        hairw2layout.setOnClickListener(this);
        hairw3layout.setOnClickListener(this);
        hairw4layout.setOnClickListener(this);
/*
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
        */



        RelativeLayout.LayoutParams layoutParamssample1 = new RelativeLayout.LayoutParams(550,500);
        hairs11.setOnTouchListener(new ChoiceTouchListener());
        layoutParamssample1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        hairs11.setLayoutParams(layoutParamssample1);

        RelativeLayout.LayoutParams layoutParamssample2 = new RelativeLayout.LayoutParams(550,500);
        layoutParamssample2.addRule(RelativeLayout.ALIGN_RIGHT, RelativeLayout.TRUE);
        hairs22.setOnTouchListener(new ChoiceTouchListener());
        hairs22.setLayoutParams(layoutParamssample2);

        RelativeLayout.LayoutParams layoutParamssample3 = new RelativeLayout.LayoutParams(550,500);
        layoutParamssample3.addRule(RelativeLayout.ALIGN_RIGHT, RelativeLayout.TRUE);
        hairs33.setOnTouchListener(new ChoiceTouchListener());
        hairs33.setLayoutParams(layoutParamssample3);

        RelativeLayout.LayoutParams layoutParamssample4 = new RelativeLayout.LayoutParams(550,500);
        layoutParamssample4.addRule(RelativeLayout.ALIGN_RIGHT, RelativeLayout.TRUE);
        hairs44.setOnTouchListener(new ChoiceTouchListener());
        hairs44.setLayoutParams(layoutParamssample4);


        RelativeLayout.LayoutParams layoutParamssamplew1 = new RelativeLayout.LayoutParams(1200,1200);
        hairsw11.setOnTouchListener(new ChoiceTouchListener());
        layoutParamssamplew1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        hairsw11.setLayoutParams(layoutParamssamplew1);

        RelativeLayout.LayoutParams layoutParamssamplew2 = new RelativeLayout.LayoutParams(1200,1200);
        layoutParamssamplew2.addRule(RelativeLayout.ALIGN_RIGHT, RelativeLayout.TRUE);
        hairsw22.setOnTouchListener(new ChoiceTouchListener());
        hairsw22.setLayoutParams(layoutParamssamplew2);

        RelativeLayout.LayoutParams layoutParamssamplew3 = new RelativeLayout.LayoutParams(1200,1200);
        layoutParamssamplew3.addRule(RelativeLayout.ALIGN_RIGHT, RelativeLayout.TRUE);
        hairsw33.setOnTouchListener(new ChoiceTouchListener());
        hairsw33.setLayoutParams(layoutParamssamplew3);

        RelativeLayout.LayoutParams layoutParamssamplew4 = new RelativeLayout.LayoutParams(1200,1200);
        layoutParamssamplew4.addRule(RelativeLayout.ALIGN_RIGHT, RelativeLayout.TRUE);
        hairsw44.setOnTouchListener(new ChoiceTouchListener());
        hairsw44.setLayoutParams(layoutParamssamplew4);











        hair1layout.setClipToOutline(true);
        hair2layout.setClipToOutline(true);
        hair3layout.setClipToOutline(true);



        hairs11.setVisibility(View.GONE);
        hairs22.setVisibility(View.GONE);
        hairs33.setVisibility(View.GONE);
        hairs44.setVisibility(View.GONE);

        wc.setVisibility(View.GONE);
        mc.setVisibility(View.VISIBLE);

        hairsw11.setVisibility(View.GONE);
        hairsw22.setVisibility(View.GONE);
        hairsw33.setVisibility(View.GONE);
        hairsw44.setVisibility(View.GONE);
        hairw1layout.setVisibility(View.GONE);
        hairw2layout.setVisibility(View.GONE);
        hairw3layout.setVisibility(View.GONE);
        hairw4layout.setVisibility(View.GONE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ProfileImage.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onClick(View v) {

        if (gender == true) {

            hairsw11.setVisibility(View.GONE);
            hairsw22.setVisibility(View.GONE);
            hairsw33.setVisibility(View.GONE);
            hairsw44.setVisibility(View.GONE);

            hairw1layout.setVisibility(View.GONE);
            hairw2layout.setVisibility(View.GONE);
            hairw3layout.setVisibility(View.GONE);
            hairw4layout.setVisibility(View.GONE);



            hair1layout.setVisibility(View.VISIBLE);
            hair2layout.setVisibility(View.VISIBLE);
            hair3layout.setVisibility(View.VISIBLE);
            hair4layout.setVisibility(View.VISIBLE);
            mc.setVisibility(View.VISIBLE);
            wc.setVisibility(View.GONE);
//-----------------

            if(v==mc){ // man -> woman
                gender = false;
                hairs11.setVisibility(View.GONE);
                hairs22.setVisibility(View.GONE);
                hairs33.setVisibility(View.GONE);
                hairs44.setVisibility(View.GONE);
                hair1layout.setVisibility(View.GONE);
                hair2layout.setVisibility(View.GONE);
                hair3layout.setVisibility(View.GONE);
                hair4layout.setVisibility(View.GONE);

                //hairsw11.setVisibility(View.VISIBLE);
                hairw1layout.setVisibility(View.VISIBLE);
                hairw2layout.setVisibility(View.VISIBLE);
                hairw3layout.setVisibility(View.VISIBLE);
                hairw4layout.setVisibility(View.VISIBLE);

                wc.setVisibility(View.VISIBLE);
                mc.setVisibility(View.GONE);
            }


            if (v == hair1layout && (hairs11.getVisibility() == View.GONE)) {
                hairs11.setX(XX);
                hairs11.setY(YY);
                hairs11.setVisibility(View.VISIBLE);
                hairs22.setVisibility(View.GONE);
                hairs33.setVisibility(View.GONE);
                hairs44.setVisibility(View.GONE);
            }
            else if (v == hair2layout && (hairs22.getVisibility() == View.GONE)) {
                hairs22.setX(XX);
                hairs22.setY(YY);
                hairs22.setVisibility(View.VISIBLE);
                hairs11.setVisibility(View.GONE);
                hairs33.setVisibility(View.GONE);
                hairs44.setVisibility(View.GONE);
            }
            else if (v == hair3layout && (hairs33.getVisibility() == View.GONE)) {
                hairs33.setX(XX);
                hairs33.setY(YY);
                hairs33.setVisibility(View.VISIBLE);
                hairs22.setVisibility(View.GONE);
                hairs11.setVisibility(View.GONE);
                hairs44.setVisibility(View.GONE);
            }
            else if (v == hair4layout && (hairs44.getVisibility() == View.GONE)) {
                hairs44.setX(XX);
                hairs44.setY(YY);
                hairs44.setVisibility(View.VISIBLE);
                hairs22.setVisibility(View.GONE);
                hairs33.setVisibility(View.GONE);
                hairs11.setVisibility(View.GONE);
            }
            else{

                hairs11.setVisibility(View.GONE);
                hairs22.setVisibility(View.GONE);
                hairs33.setVisibility(View.GONE);
                hairs44.setVisibility(View.GONE);
            }
        }


        else{
            hairs11.setVisibility(View.GONE);
            hairs22.setVisibility(View.GONE);
            hairs33.setVisibility(View.GONE);
            hairs44.setVisibility(View.GONE);
            hair1layout.setVisibility(View.GONE);
            hair2layout.setVisibility(View.GONE);
            hair3layout.setVisibility(View.GONE);
            hair4layout.setVisibility(View.GONE);


            hairw1layout.setVisibility(View.VISIBLE);
            hairw2layout.setVisibility(View.VISIBLE);
            hairw3layout.setVisibility(View.VISIBLE);
            hairw4layout.setVisibility(View.VISIBLE);

            wc.setVisibility(View.VISIBLE);
            mc.setVisibility(View.GONE);
            if(v== wc){ // woman -> man
                gender = true;
                hairsw11.setVisibility(View.GONE);
                hairsw22.setVisibility(View.GONE);
                hairsw33.setVisibility(View.GONE);
                hairsw44.setVisibility(View.GONE);

                hairw1layout.setVisibility(View.GONE);
                hairw2layout.setVisibility(View.GONE);
                hairw3layout.setVisibility(View.GONE);
                hairw4layout.setVisibility(View.GONE);



                hair1layout.setVisibility(View.VISIBLE);
                hair2layout.setVisibility(View.VISIBLE);
                hair3layout.setVisibility(View.VISIBLE);
                hair4layout.setVisibility(View.VISIBLE);
                mc.setVisibility(View.VISIBLE);
                wc.setVisibility(View.GONE);}


            if (v == hairw1layout && (hairsw11.getVisibility() == View.GONE)) {
                hairsw11.setX(XX);
                hairsw11.setY(YY);

                hairsw11.setVisibility(View.VISIBLE);
                hairsw22.setVisibility(View.GONE);
                hairsw33.setVisibility(View.GONE);
                hairsw44.setVisibility(View.GONE);
            }
            else if (v == hairw2layout && (hairsw22.getVisibility() == View.GONE)) {
                hairsw22.setX(XX);
                hairsw22.setY(YY);

                hairsw22.setVisibility(View.VISIBLE);
                hairsw11.setVisibility(View.GONE);
                hairsw33.setVisibility(View.GONE);
                hairsw44.setVisibility(View.GONE);
            }
            else if (v == hairw3layout && (hairsw33.getVisibility() == View.GONE)) {
                hairsw33.setX(XX);
                hairsw33.setY(YY);

                hairsw33.setVisibility(View.VISIBLE);
                hairsw22.setVisibility(View.GONE);
                hairsw11.setVisibility(View.GONE);
                hairsw44.setVisibility(View.GONE);
            }
            else if (v == hairw4layout && (hairsw44.getVisibility() == View.GONE)) {
                hairsw44.setX(XX);
                hairsw44.setY(YY);

                hairsw44.setVisibility(View.VISIBLE);
                hairsw22.setVisibility(View.GONE);
                hairsw33.setVisibility(View.GONE);
                hairsw11.setVisibility(View.GONE);
            }
            else{

                hairsw11.setVisibility(View.GONE);
                hairsw22.setVisibility(View.GONE);
                hairsw33.setVisibility(View.GONE);
                hairsw44.setVisibility(View.GONE);
            }


        }
    }


    // onTouch
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    /*
    @Override
    public  boolean onTouchEvent(MotionEvent event){
        if(event.getPointerCount() == 2){
            int action = event.getAction();
            int mainaction = action&MotionEvent.ACTION_MASK;
            if(mainaction == MotionEvent.ACTION_POINTER_DOWN){
                baseDist = getDistance(event);
                baseRatio = ratio;
            }else{
                float scale = (getDistance(event)-baseDist)/move;
                float factor = (float)Math.pow(1,scale);
                ratio = Math.min(1024.0f,Math.max(0.1f,baseRatio*factor));
                //ratio = Math.min(0.1f,baseRatio*factor);
               // ratio = Math.max(1024.f,)
                //hairs11.setImage(ratio+15);
            }
        }
        return true;
    }
    */
/*
    private int getDistance(MotionEvent event) {
        int dx = (int) (event.getX(0)-event.getX(1));
        int dy = (int) (event.getY(0)-event.getY(1));
        //return (int) (Math.sqrt(dx*dx + dy*dy));
        Log.d("value of dx,dy", "dx*dx : " + dx*dx+ " dy*dy" + dy*dy );
        return (int)(dx*dx + dy*dy);

    }
    */
/*
    //gesture
    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
// gesture
*/
    private final class ChoiceTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent event){
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            Log.i("mouse position :","X :"+XX + " Y : " + YY + "");
            switch(event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    _xDelta = X - lParams.leftMargin;
                    _yDelta = Y - lParams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    XX = X-_xDelta;
                    YY = Y-_yDelta;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.leftMargin = X - _xDelta;
                    layoutParams.topMargin = Y - _yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    view.setLayoutParams(layoutParams);
                    break;
            }

            return true;


        }
    }

}
