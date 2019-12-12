package com.example.hairmall2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    }


}
