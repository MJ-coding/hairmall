package com.example.hairmall2.ui;

import android.content.Intent;
import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ImageView review_imageview_sec;
    private ImageView review_imageview_thir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act10_review1);
        //리뷰저장버튼
        Button saveButton = (Button)findViewById(R.id.save_button);

        final TextView writeReview = (EditText)findViewById(R.id.write_review);
        final TextView writeRank = (EditText)findViewById(R.id.write_rank);
        final TextView writeStyle = (EditText)findViewById(R.id.write_style);



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
                //DB에 리뷰 정보 넣기
                Map<String, Object> data_info = new HashMap<>();
                data_info.put("shop_name", shop_name);
                data_info.put("gpa", writeRank.getText().toString());//평점
                //DB에 현재 시간 넣기
                data_info.put("date_day", date);
                data_info.put("review", writeReview.getText().toString());
                data_info.put("style", writeStyle.getText().toString());



                // Add a new document with a generated ID
                db.collection("review")
                        .add(data_info)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("sucess", "DocumentSnapshot added with ID: "
                                        + documentReference.getId());
                                Toast myToast = Toast.makeText(getApplicationContext(),
                                        "저장이 완료 되었습니다.", Toast.LENGTH_SHORT);
                                myToast.show();
                                onBackPressed();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("fail", "Error adding document", e);
                                Toast myToast = Toast.makeText(getApplicationContext(),
                                        "저장이 실패하였습니다..", Toast.LENGTH_SHORT);
                                myToast.show();
                            }
                        });
            }
        });
        //리뷰작성 취소시 그전 act8_shopdetail로 이
        Button cancelButton =(Button)findViewById(R.id.review_cancel);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),act8_shopdetail.class);
                startActivity(intent);

            }
        });



        //리뷰이미지 갤러리에서 가져오기
        review_imageview_fir = (ImageView)findViewById(R.id.register_fir_photo);
        review_imageview_sec = (ImageView)findViewById(R.id.register_sec_photo);
        review_imageview_thir = (ImageView)findViewById(R.id.register_thir_photo);

        review_imageview_fir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                //사진 여러장 가져오기
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");
                startActivityForResult(intent.createChooser(intent,"choose picture"),
                        PICK_GALLERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_GALLERY && resultCode == RESULT_OK ) {

            Uri selectedImageUri = data.getData();
            ClipData clipData = data.getClipData();

            if(clipData!=null)
            {

                for(int i = 0; i < 3; i++)
                {
                    if(i<clipData.getItemCount()){
                        Uri uri =  clipData.getItemAt(i).getUri();
                        switch (i){
                            case 0:
                                review_imageview_fir.setImageURI(uri);

                                break;
                            case 1:
                                review_imageview_sec.setImageURI(uri);
                                break;
                            case 2:
                                review_imageview_thir.setImageURI(uri);
                                break;
                        }
                    }
                }
            }
            else if(selectedImageUri != null)
            {
                review_imageview_fir.setImageURI(selectedImageUri);
            }

        }


    }

}
