package com.example.hairmall2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hairmall2.R;
import com.example.hairmall2.MainPage;
import com.example.hairmall2.shop;
import com.example.hairmall2.user1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class act3_login extends AppCompatActivity {

    private EditText edit_login_id;
    private EditText edit_login_pw;
    private Button btn_login_login;
    private CheckBox check_login_autologin;
    private Button btn_login_idfind;
    private Button btn_login_pwfind;
    private Button btn_login_join;
    private Button btn_login_nobodylogin;

    private CheckBox check_login_users;
    private CheckBox check_login_shops;

    private String id;
    private String pw;
    private String realpw;
    private String realpwShop;
    private boolean IsExistpw;

    private String class_name = "users";

    static ArrayList<String> arrayIndex = new ArrayList<>();
    static ArrayList<String> arrayData = new ArrayList<>();

    static ArrayList<String> arrayIndexShops = new ArrayList<>();
    static ArrayList<String> arrayDataShops = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act3_login);

        edit_login_id =  findViewById(R.id.edit_login_id);
        edit_login_pw = findViewById(R.id.edit_login_pw);
        btn_login_login = findViewById(R.id.btn_login_login);
        check_login_autologin = findViewById(R.id.check_login_autologin);
        btn_login_idfind = findViewById(R.id.btn_login_idfind);
        btn_login_pwfind = findViewById(R.id.btn_login_pwfind);
        btn_login_join = findViewById(R.id.btn_login_join);
        btn_login_nobodylogin = findViewById(R.id.btn_login_nobodylogin);

        check_login_users = findViewById(R.id.check_login_users);
        check_login_shops = findViewById(R.id.check_login_shops);

         Log.d("login_DEBUG01","Shop : 01" );
    }

    public boolean IsExistId(){
        boolean IsExist = arrayIndex.contains(id);
        return IsExist;
    }

    public boolean IsExistIdShop(){
        boolean IsExist = arrayIndexShops.contains(id);
        return IsExist;
    }
  /*
    public boolean IsExistPw(String id, String password){
        boolean IsExist;
        String realpw = mRootRef.child("hairmall").child("users").child(id).child(pw).getValue(String.class);
        if (realpw == password){
            return true;
        }else{
            return false;
        }
    }
*/
    public void setEditBlank(){
        edit_login_id.setText("");
        edit_login_pw.setText("");
    }


    public void IsExistPw(){

        id = edit_login_id.getText().toString();
        pw = edit_login_pw.getText().toString();

        Log.d("DEBUG","DEBUG1 : ");
        childRef = mRootRef.child("hairmall").child("users").child(id).child("pw");
        Log.d("DEBUG","DEBUG2 : ");
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                realpw = dataSnapshot.getValue(String.class);
                if(IsExistId()){
               //     Log.d("getFirebaseDatabaes","DEBUG : " + IsExistpw);
                    if(pw.equals(realpw)){

                        setIdAllActivity();
                        setEditBlank();
                        Intent intent = new Intent(act3_login.this, MainPage.class);
                        intent.putExtra("id", id);
                        intent.putExtra("class_name", class_name);
                        startActivity(intent);
                    }else{
                        Toast.makeText(act3_login.this, "비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                        setEditBlank();
                    }

                }else{
                    Toast.makeText(act3_login.this, "존재하지 않는 아이디 입니다.",Toast.LENGTH_LONG).show();
                    setEditBlank();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });
    }

    public void IsExistPwShop(){
        id = edit_login_id.getText().toString();
        pw = edit_login_pw.getText().toString();

        Log.d("DEBUG","DEBUG1 : ");
        childRef = mRootRef.child("hairmall").child("shops").child(id).child("pw");
        Log.d("DEBUG","DEBUG2 : ");
        Log.d("shopsDEBUG","DEBUG0 : " + realpwShop);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                realpwShop = dataSnapshot.getValue(String.class);
                Log.d("shopsDEBUG","DEBUG1 : " + realpwShop);
                if(IsExistIdShop()){
                    Log.d("shopsDEBUG","DEBUG2 : " + realpwShop);
           //         Log.d("getFirebaseDatabaes","DEBUGShop : " + IsExistpw);
                    if(pw.equals(realpwShop)){
                        Log.d("shopsDEBUG","DEBUG3 : " + realpwShop);
                        setIdAllActivity();
                        setEditBlank();
                        Intent intent = new Intent(act3_login.this, MainPage.class);
                        intent.putExtra("id", id);
                        intent.putExtra("class_name", class_name);

                        startActivity(intent);
                    }else{
                        Log.d("shopsDEBUG","DEBUG4 : " + realpwShop);
                        Toast.makeText(act3_login.this, "비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                        setEditBlank();
                    }

                }else{
                    Toast.makeText(act3_login.this, "존재하지 않는 아이디 입니다.",Toast.LENGTH_LONG).show();
                    setEditBlank();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });
    }



    public void getFirebaseDatabase(){
        Log.d("login_DEBUG02","Shop" );
        childRef = mRootRef.child("hairmall").child("users");
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    arrayData.clear();
                    arrayIndex.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        String key = ds.getKey();
                        user1 user = ds.getValue(user1.class);
                        String[] info = {user.getEmail(), user.getName(), user.getPhone(), user.getId(), user.getPw(),user.getMemo()};//ArrayStar 제외
                        String Result = info[0] + " " + info[1] + " " + info[2] + " " + info[3] + " " + info[4] + " " + info[5];
                        arrayIndex.add(key);
                        Log.d("getFirebaseDatabaes","key : " + key);
                        arrayData.add(Result);
                        Log.d("getFirebaseDatabase", "info : " + info[0] + info[1] + info[2] + info[3]+ info[4] + info[5]);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });

    }

    public void getFirebaseDatabaseShop(){
        Log.d("login_DEBUG03","Shop" );
        childRef = mRootRef.child("hairmall").child("shops");
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    arrayDataShops.clear();
                    arrayIndexShops.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        String key = ds.getKey();
                        shop shop = ds.getValue(shop.class);
                        String[] info = {shop.getEmail(), shop.getName(), shop.getPhone(), shop.getId(), shop.getPw(),shop.getMemo()};//ArrayStar 제외
                        String Result = info[0] + " " + info[1] + " " + info[2] + " " + info[3] + " " + info[4] + " " + info[5];
                        arrayIndexShops.add(key);
                        Log.d("getFirebaseDatabaes","key : " + key);
                        arrayDataShops.add(Result);
                        Log.d("getFirebaseDatabase", "info : " + info[0] + info[1] + info[2] + info[3]+ info[4] + info[5]);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });

    }



    public void setIdAllActivity(){
        Log.d("login_DEBUG04","Shop : 04" );
        Intent intent = new Intent(act3_login.this, com.example.hairmall2.ui.home.act1_mainpage.class);
        intent.putExtra("id", id);
        intent.putExtra("class_name", class_name);

        Log.d("login_DEBUG05","Shop : 05" );

        intent = new Intent(act3_login.this, com.example.hairmall2.MainPage.class);
        intent.putExtra("id", id);
        intent.putExtra("class_name", class_name);

        Log.d("login_DEBUG06","Shop : 06" );

        //intent = new Intent(act3_login.this, com.example.hairmall2.ui.act5_mypage.class); //에러남
        //intent.putExtra("id", id);
    }

    @Override
    protected void onStart(){
        super.onStart();

        class_name = "users";
        check_login_shops.setChecked(false);
        check_login_users.setChecked(true);

        getFirebaseDatabase();
        getFirebaseDatabaseShop();


        btn_login_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if (class_name.equals("users")){

                    IsExistPw();

                } else if(class_name.equals("shops")){

                    IsExistPwShop();

                }



            }

        });



        btn_login_join.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.btn_login_join:

                        Intent intent = new Intent(act3_login.this, act4_join.class);
                        startActivity(intent);

                        break;

                }

            }

        });


        check_login_users.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                check_login_users.setChecked(true);
                check_login_shops.setChecked(false);
                class_name = "users";

            }

        });

        check_login_shops.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                check_login_shops.setChecked(true);
                check_login_users.setChecked(false);
                class_name = "shops";

            }

        });


    }


}
