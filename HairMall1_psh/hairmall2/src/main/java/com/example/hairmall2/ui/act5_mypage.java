package com.example.hairmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hairmall2.R;
import com.example.hairmall2.user1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class act5_mypage extends AppCompatActivity {

    private EditText edit_mypage_date;
    private EditText edit_mypage_shop;
    private EditText edit_mypage_time;
    private Button btn_mypage_setTime;

    private EditText edit_mypage_name;
    private EditText edit_mypage_phone;
    private EditText edit_mypage_email;
    private EditText edit_mypage_pw;
    private Button btn_mypage_setUser;

    private String email;
    private String name;
    private String phone;
    private String id;
    private String pw;
    private String memo;
    private ArrayList<String> ArrayStar = new ArrayList<>();

    private String memo_date;
    private String memo_shop;
    private String memo_time;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-test-7d2fa.firebaseio.com");
    DatabaseReference childRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act5_mypage);

        Intent intent=new Intent(this.getIntent()); // MainPage로부터 받아옴
        id=intent.getStringExtra("id");
        Log.d("DEBUG_mypage",id);

        edit_mypage_date = findViewById(R.id.edit_mypage_date);
        edit_mypage_shop = findViewById(R.id.edit_mypage_shop);
        edit_mypage_time = findViewById(R.id.edit_mypage_time);
        btn_mypage_setTime = findViewById(R.id.btn_mypage_setTime);

        edit_mypage_name = findViewById(R.id.edit_mypage_name);
        edit_mypage_phone = findViewById(R.id.edit_mypage_phone);
        edit_mypage_email = findViewById(R.id.edit_mypage_email);
        edit_mypage_pw = findViewById(R.id.edit_mypage_pw);
        btn_mypage_setUser = findViewById(R.id.btn_mypage_setUser);
    }

    public final void user_setTime(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> values = null;
        if(add){
            user1 user = new user1(email, name, phone, id, pw, memo, ArrayStar, memo_date, memo_shop, memo_time);
            values = user.setTime();
        }
        values.put("id",id);
        values.put("email", email);
        values.put("name", name);
        values.put("phone", phone);
        values.put("pw", pw);
        childUpdates.put("/hairmall/users/"+id, values);
        mRootRef.updateChildren(childUpdates);

    }

    public final void user_setUser(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> values = null;
        if(add){
            user1 user = new user1(email, name, phone, id, pw, memo, ArrayStar, memo_date, memo_shop, memo_time);
            values = user.setUser();
        }
        values.put("id",id);
        values.put("memo_date", memo_date);
        values.put("memo_shop", memo_shop);
        values.put("memo_time", memo_time);
        childUpdates.put("/hairmall/users/"+id, values);
        mRootRef.updateChildren(childUpdates);

    }

    public void getFirebaseDatabase(){
        childRef = mRootRef.child("hairmall").child("users").child(id);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    user1 user = dataSnapshot.getValue(user1.class);

                    edit_mypage_date.setText(user.memo_date);
                    edit_mypage_shop.setText(user.memo_shop);
                    edit_mypage_time.setText(user.memo_time);

                    edit_mypage_name.setText(user.name);
                    edit_mypage_phone.setText(user.phone);
                    edit_mypage_email.setText(user.email);
                    edit_mypage_pw.setText(user.pw);

                    String[] info = {user.getEmail(), user.getName(), user.getName(), user.getId(), user.getPw(),user.getMemo(),user.getMemo_date(),user.getMemo_shop(),user.getMemo_time()};//ArrayStar 제외
                    String Result = info[0] + " " + info[1] + " " + info[2] + " " + info[3] + " " + info[4] + " " + info[5] + " " + info[6] + " " + info[7] + " " + info[8];
                    Log.d("getFirebaseDatabase", "info : " + Result);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });

    }

    @Override
    public void onStart(){
        super.onStart();
        getFirebaseDatabase();

        btn_mypage_setTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                memo_date = edit_mypage_date.getText().toString();
                memo_shop = edit_mypage_shop.getText().toString();
                memo_time = edit_mypage_time.getText().toString();

                name = edit_mypage_name.getText().toString();
                phone = edit_mypage_phone.getText().toString();
                email = edit_mypage_email.getText().toString();
                pw = edit_mypage_pw.getText().toString();

                user_setTime(true);

            }


        });

        btn_mypage_setUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                memo_date = edit_mypage_date.getText().toString();
                memo_shop = edit_mypage_shop.getText().toString();
                memo_time = edit_mypage_time.getText().toString();

                name = edit_mypage_name.getText().toString();
                phone = edit_mypage_phone.getText().toString();
                email = edit_mypage_email.getText().toString();
                pw = edit_mypage_pw.getText().toString();

                user_setUser(true);

            }


        });



    }



}
