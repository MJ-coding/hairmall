package com.example.hairmall2.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hairmall2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class act10_review extends AppCompatActivity {

    //request code_
    private final int PICK_GALLERY = 1;
    private ImageView review_imageview_fir;
   // private ImageView review_imageview_sec;
    //private ImageView review_imageview_thir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act10_review1);

        Button saveButton = (Button)findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //다시 이름 가져오기
                String shop_name = getIntent().getStringExtra("shop_name");
                String gpa = getIntent().getStringExtra("gpa");

                //현재 시간 넣기
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = sdf.format(new Date());

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                //DB에 넣기
                Map<String, Object> data_info = new HashMap<>();
                //*****추가구현할 부분***가게 이름 넣은듯이 다른부분도 넣기
                data_info.put("shop_name", shop_name);
                data_info.put("gpa", gpa);//평점

                //DB에 현재 시간 넣기
                data_info.put("date_day", date);



                // Add a new document with a generated ID
                db.collection("users")
                        .add(data_info)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("sucess", "DocumentSnapshot added with ID: "
                                        + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("fail", "Error adding document", e);
                            }
                        });
            }
        });



        //리뷰이미지 갤러리에서 가져오기
        review_imageview_fir = (ImageView)findViewById(R.id.register_fir_photo);
     //   review_imageview_sec = (ImageView)findViewById(R.id.register_sec_photo);
     //   review_imageview_thir = (ImageView)findViewById(R.id.register_thir_photo);

        review_imageview_fir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent, PICK_GALLERY);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_GALLERY && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri selectedImageUri = data.getData();
            review_imageview_fir.setImageURI(selectedImageUri);

        }


    }
}
