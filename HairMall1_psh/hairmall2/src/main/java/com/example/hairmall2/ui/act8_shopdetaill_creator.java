package com.example.hairmall2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hairmall2.R;
import com.example.hairmall2.shop;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class act8_shopdetaill_creator extends AppCompatActivity {

    private static final int REQUEST_CODE1 = 1;
    private static final int REQUEST_CODE2 = 2;
    private static final int REQUEST_CODE3 = 3;
    private static final int REQUEST_CODE4 = 4;
    private static final int REQUEST_CODE5 = 5;
    private static final int REQUEST_CODE6 = 6;
    private static final int REQUEST_CODE7 = 7;
    private static final int REQUEST_CODE8 = 8;
    private static final int REQUEST_CODE9 = 9;
    private static final int REQUEST_CODE10 = 10;
    private static final int REQUEST_CODE11 = 11;
    private static final int REQUEST_CODE12 = 12;

    private ImageView imag_creator_shop;
    private ImageView imag_creator_main1;
    private ImageView imag_creator_main2;
    private ImageView imag_creator_main3;

    private ImageView imag_creator_menu1;
    private ImageView imag_creator_menu2;
    private ImageView imag_creator_menu3;
    private ImageView imag_creator_menu4;

    private ImageView imag_creator_review1;
    private ImageView imag_creator_review2;
    private ImageView imag_creator_review3;
    private ImageView imag_creator_review4;

    private TextView text_creator_name;
    private Button btn_creator_create;

    private TextView text_creator_glideuri;
    private ImageView imag_creator_glidetest;

    private String id;
    private String id_uri;
    private String id_info;

    private String email;
    private String name;
    private String phone;
    private String pw;
    private String memo;

    private String class_name="shops";

    private ArrayList<String> ArrayStar = new ArrayList<>();

    private String memo_date;
    private String memo_shop;
    private String memo_time;

    public String shop_main_url;

    public String shop_imag_url1;
    public String shop_imag_url2;
    public String shop_imag_url3;

    public String shop_menu_url1;
    public String shop_menu_url2;
    public String shop_menu_url3;
    public String shop_menu_url4;

    public String shop_review_url1;
    public String shop_review_url2;
    public String shop_review_url3;
    public String shop_review_url4;

    public String reputation;

    public ArrayList<String> arrayReserve = new ArrayList<>();
    public ArrayList<String> arrayReview = new ArrayList<>();

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-test-7d2fa.firebaseio.com");
    DatabaseReference childRef;


    //private Bitmap imag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("DEBUG01");
        Log.d("creator","DEBUG01");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act8_shopdetaill_creator);

        Intent intent=new Intent(this.getIntent()); // MainPage로부터 받아옴
        id=intent.getStringExtra("id");
        id_uri = id + "_uri";
        id_info = id + "_info";

        imag_creator_shop = findViewById(R.id.imag_creator_shop);
        imag_creator_main1 = findViewById(R.id.imag_creator_main1);
        imag_creator_main2 = findViewById(R.id.imag_creator_main2);
        imag_creator_main3 = findViewById(R.id.imag_creator_main3);

        imag_creator_menu1 = findViewById(R.id.imag_creator_menu1);
        imag_creator_menu2 = findViewById(R.id.imag_creator_menu2);
        imag_creator_menu3 = findViewById(R.id.imag_creator_menu3);
        imag_creator_menu4 = findViewById(R.id.imag_creator_menu4);

        imag_creator_review1 = findViewById(R.id.imag_creator_review1);
        imag_creator_review2 = findViewById(R.id.imag_creator_review2);
        imag_creator_review3 = findViewById(R.id.imag_creator_review3);
        imag_creator_review4 = findViewById(R.id.imag_creator_review4);

        text_creator_name = findViewById(R.id.text_creator_name);
        btn_creator_create = findViewById(R.id.btn_creator_create);

        text_creator_glideuri = findViewById(R.id.text_creator_glideuri);
        imag_creator_glidetest = findViewById(R.id.imag_creator_glidetest);


        text_creator_name.setText(id);

        Log.d("shopdetail_DEBUG01", "00");
        includesForUploadFiles(imag_creator_main3,id,"shop_test");

    }

    public void includesForUploadFiles(ImageView v, String shop_name, String jpg_name) {
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageRef = storage.getReference();

        StorageReference mountainsRef = storageRef.child("mountains.jpg");
        final StorageReference mountainImagesRef = storageRef.child("hairmall/shops/"+shop_name+"/"+jpg_name+".jpg");

        ImageView imageView = v; //findViewById(R.id.imag_creator_shop);

        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        Log.d("shopdetail_DEBUG01", "03");

        UploadTask uploadTask = mountainImagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //taskSnapshot.getMetadata() contains file metadata such as size
            }
        });

        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()){
                    throw task.getException();
                }

                return mountainImagesRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult(); //예측불가
                    String downloadUristring = downloadUri.toString();
                    Glide.with(com.example.hairmall2.ui.act8_shopdetaill_creator.this).load(downloadUristring).into(imag_creator_glidetest);
                    Log.d("ABCD", downloadUristring);
                    text_creator_glideuri.setText(downloadUristring);
                } else {
                    //Handle failures
                }
            }
        });


    }

    public final void adduri(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> values = null;
        if(add){
            shop shop = new shop(email, name, phone, id, pw,
                    memo, ArrayStar, memo_date, memo_shop, memo_time,
                    class_name,
                    shop_main_url, shop_imag_url1, shop_imag_url2, shop_imag_url3,
                    shop_menu_url1, shop_menu_url2, shop_menu_url3, shop_menu_url4,
                    shop_review_url1, shop_review_url2, shop_review_url3, shop_review_url4,
                    reputation,
                    arrayReserve, arrayReview);
            values = shop.toMapUri();
        }
        childUpdates.put("/hairmall/shops/"+id+"/"+id_uri, values);
        mRootRef.updateChildren(childUpdates);
    }

    public void getFirebaseDatabaseShop_uri(){
        childRef = mRootRef.child("hairmall").child("shops").child(id).child(id_uri);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    shop shop = dataSnapshot.getValue(com.example.hairmall2.shop.class);

                    shop_main_url = shop.shop_main_url;

                    shop_imag_url1 = shop.shop_imag_url1;
                    shop_imag_url2 = shop.shop_imag_url2;
                    shop_imag_url3 = shop.shop_imag_url3;

                    shop_menu_url1 = shop.shop_menu_url1;
                    shop_menu_url2 = shop.shop_menu_url2;
                    shop_menu_url3 = shop.shop_menu_url3;
                    shop_menu_url4 = shop.shop_menu_url4;

                    shop_review_url1 = shop.shop_review_url1;
                    shop_review_url2 = shop.shop_review_url2;
                    shop_review_url3 = shop.shop_review_url3;
                    shop_review_url4 = shop.shop_review_url4;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });

    }


        @Override
    public void onStart() {
        super.onStart();

        /*
        getFirebaseDatabaseShop_uri();
        Glide.with(com.example.hairmall2.ui.act8_shopdetaill_creator.this).load(shop_main_url).into(imag_creator_menu1);
        text_creator_name.setText(shop_main_url);
        */
        btn_creator_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adduri(true);
            }
        });

        imag_creator_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE1);


            }
        });

        imag_creator_main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE2);

                Log.d("shopdetail","View" + v.toString());
            }
        });

        imag_creator_main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE3);

            }
        });

        imag_creator_main3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE4);


            }
        });

        imag_creator_menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE5);

            }
        });

        imag_creator_menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE6);

            }
        });

        imag_creator_menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE7);

            }
        });

        imag_creator_menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE8);

            }
        });

        imag_creator_review1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE9);

            }
        });

        imag_creator_review2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE10);

            }
        });

        imag_creator_review3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE11);

            }
        });

        imag_creator_review4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE12);

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);

        if(requestCode == REQUEST_CODE1){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_shop.setImageBitmap(imag);
                    in.close();
                    includesForUploadFiles(imag_creator_shop,id,"shop_imag");
                    shop_main_url = text_creator_glideuri.getText().toString();



                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE2){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_main1.setImageBitmap(imag);
                    in.close();
                    includesForUploadFiles(imag_creator_main1,id,"main1_imag");
                    shop_imag_url1 = text_creator_glideuri.getText().toString();



                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE3){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_main2.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_main2,id,"main2_imag");
                    shop_imag_url2 = text_creator_glideuri.getText().toString();



                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE4){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_main3.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_main3,id,"main3_imag");
                    shop_imag_url3 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE5){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_menu1.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_menu1,id,"menu1_imag");
                    shop_menu_url1 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE6){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_menu2.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_menu2,id,"menu2_imag");
                    shop_menu_url2 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE7){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_menu3.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_menu3,id,"menu3_imag");
                    shop_menu_url3 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE8){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_menu4.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_menu4,id,"menu4_imag");
                    shop_menu_url4 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE9){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_review1.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_review1,id,"review1_imag");
                    shop_review_url1 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE10){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_review2.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_review2,id,"review2_imag");
                    shop_review_url2 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE11){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_review3.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_review3,id,"review3_imag");
                    shop_review_url3 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }

        if(requestCode == REQUEST_CODE12){

            if(resultCode == RESULT_OK){

                try{

                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap imag = BitmapFactory.decodeStream(in);
                    imag_creator_review4.setImageBitmap(imag);
                    in.close();

                    includesForUploadFiles(imag_creator_review4,id,"review4_imag");
                    shop_review_url4 = text_creator_glideuri.getText().toString();


                }catch (Exception e){ }

            } else if(resultCode == RESULT_CANCELED){
                Toast.makeText(com.example.hairmall2.ui.act8_shopdetaill_creator.this, "사진 선택 취소" ,Toast.LENGTH_LONG).show();
            }

        }







    }



}
