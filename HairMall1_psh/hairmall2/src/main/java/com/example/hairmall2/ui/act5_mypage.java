package com.example.hairmall2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hairmall2.R;
import com.example.hairmall2.act5_mypage_shopAdapter;
import com.example.hairmall2.shop;
import com.example.hairmall2.user1;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class act5_mypage extends AppCompatActivity implements act5_mypage_shopAdapter.OnItemClickListener {

    private EditText edit_mypage_date;
    private EditText edit_mypage_shop;
    private EditText edit_mypage_time;
    private Button btn_mypage_setTime;

    private EditText edit_mypage_name;
    private EditText edit_mypage_phone;
    private EditText edit_mypage_email;
    private EditText edit_mypage_pw;
    private Button btn_mypage_setUser;

    private Button btn_mypage_creator;

    private String email;
    private String name;
    private String phone;
    private String id;
    private String pw;
    private String memo;
    private String id_info;

    private String class_name;

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

    public ArrayList<String> arrayStar = new ArrayList<>();

    private RecyclerView recycler_mypage_shop;
    private act5_mypage_shopAdapter mact5_mypage_shopAdapter;
    private Context mContext;

    public String id_star;

    private LinearLayout layout_mypage_shops;
    private LinearLayout layout_mypage_shop_names;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-test-7d2fa.firebaseio.com");
    DatabaseReference childRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act5_mypage);

        mContext = this;

        Intent intent=new Intent(this.getIntent()); // MainPage로부터 받아옴
        id=intent.getStringExtra("id");
        class_name=intent.getStringExtra("class_name");

        Log.d("DEBUG_mypage",id);
        Log.d("DEBUG2_mypage_name",class_name);

        edit_mypage_date = findViewById(R.id.edit_mypage_date);
        edit_mypage_shop = findViewById(R.id.edit_mypage_shop);
        edit_mypage_time = findViewById(R.id.edit_mypage_time);
        btn_mypage_setTime = findViewById(R.id.btn_mypage_setTime);

        edit_mypage_name = findViewById(R.id.edit_mypage_name);
        edit_mypage_phone = findViewById(R.id.edit_mypage_phone);
        edit_mypage_email = findViewById(R.id.edit_mypage_email);
        edit_mypage_pw = findViewById(R.id.edit_mypage_pw);
        btn_mypage_setUser = findViewById(R.id.btn_mypage_setUser);

        btn_mypage_creator = findViewById(R.id.btn_mypage_creator);
        btn_mypage_creator.setVisibility(View.GONE);

        layout_mypage_shops = findViewById(R.id.layout_mypage_shops);
        layout_mypage_shop_names = findViewById(R.id.layout_mypage_shop_names);

        layout_mypage_shops.setVisibility(View.GONE);
        layout_mypage_shop_names.setVisibility(View.GONE);

        if (class_name.equals("shops")){
            btn_mypage_creator.setVisibility(View.VISIBLE);
        }

        //arrayStar.add("xino");
        //arrayStar.add("riahn");
        //arrayStar.add("leechul");
        //arrayStar.add("pschair");

        Log.e("RecyclerView ::", "A");




    }

    private void recycler_init(){
        recycler_mypage_shop = findViewById(R.id.recycler_mypage_shop);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        recycler_mypage_shop.setLayoutManager(mLayoutManager);

        mact5_mypage_shopAdapter = new act5_mypage_shopAdapter(mContext, arrayStar);
        mact5_mypage_shopAdapter.setOnitemClickListener(mOnItemClickListener);
        recycler_mypage_shop.setAdapter(mact5_mypage_shopAdapter);

        ArrayList<String> A = arrayStar;

        ArrayList<String> B = mact5_mypage_shopAdapter.list_string;



    }

    private act5_mypage_shopAdapter.OnItemClickListener mOnItemClickListener = new act5_mypage_shopAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, String s) {
            Log.e("RecyclerView ::", s);
            int pos = mact5_mypage_shopAdapter.pos;
            Log.e("RecyclerView ::", Integer.toString(pos));
        }
    };

    @Override
    public void onItemClick(View view, String str){
        Log.e("RecyclerView ::", str);
    }

    public final void user_setTime(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> values = null;
        if(add){
            user1 user = new user1(email, name, phone, id, pw, memo, ArrayStar, memo_date, memo_shop, memo_time , class_name);
            values = user.setTime();
        }
        values.put("id",id);
        values.put("email", email);
        values.put("name", name);
        values.put("phone", phone);
        values.put("pw", pw);
        values.put("class_name", class_name );
        childUpdates.put("/hairmall/users/"+id+"/"+id+"_info", values);
        mRootRef.updateChildren(childUpdates);

    }

    public final void user_setUser(boolean add){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> values = null;
        if(add){
            user1 user = new user1(email, name, phone, id, pw, memo, ArrayStar, memo_date, memo_shop, memo_time, class_name);
            values = user.setUser();
        }
        values.put("id",id);
        values.put("memo_date", memo_date);
        values.put("memo_shop", memo_shop);
        values.put("memo_time", memo_time);
        values.put("class_name", class_name );
        childUpdates.put("/hairmall/users/"+id+"/"+id+"_info", values);
        mRootRef.updateChildren(childUpdates);

    }

    public final void addshop(boolean add){
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
            values = shop.toMap();
        }
        childUpdates.put("/hairmall/shops/"+id+"/"+id+"_info", values);
        mRootRef.updateChildren(childUpdates);
    }

    public void getFirebaseDatabase(){
        id_info = id+"_info";
        childRef = mRootRef.child("hairmall").child("users").child(id).child(id_info);
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

    public void getFirebaseDatabaseStar(){
        Log.d("Recycler","star : " + "SSTTAARRTT");
        id_star = id+"_star";
        childRef = mRootRef.child("hairmall").child(class_name).child(id).child(id_star);
        Log.d("Recycler","star class_name : " + class_name);
        Log.d("Recycler","star id : " + id);
        Log.d("Recycler","star  id_star : " + id_star);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    arrayStar.clear();
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        String key = ds.getKey();
                        String star = ds.getValue(String.class);


                        if (star.equals("star")) {
                            //arrayStar.add(key);
                            Log.d("Recycler","star : " + star);
                            getFirebaseDatabaseStarUri(key);
                        }else {
                            Log.d("Recycler","star : " + "NNNNN");
                        }



                        //recycler_init();
                        //mact5_mypage_shopAdapter.notifyDataSetChanged();


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });

    }

    public void getFirebaseDatabaseStarUri(String shop_id){
        final String temp_shop_id = shop_id;

        childRef = mRootRef.child("hairmall").child("shops").child(shop_id).child(shop_id+"_uri").child("shop_main_url");

        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String uri = dataSnapshot.getValue(String.class);
                    String[] info = {temp_shop_id, uri};
                    String Result = info[0]+" "+info[1];
                    arrayStar.add(Result);
                    Log.d("Recycler","arraysize" + arrayStar.size());
                    Log.d("Recycler","key"+Result);

                    recycler_init();
                    mact5_mypage_shopAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });



    }

    public void getFirebaseDatabaseShop(){
        id_info = id+"_info";
        childRef = mRootRef.child("hairmall").child("shops").child(id).child(id_info);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    shop shop = dataSnapshot.getValue(com.example.hairmall2.shop.class);

                    edit_mypage_date.setText(shop.memo_date);
                    edit_mypage_shop.setText(shop.memo_shop);
                    edit_mypage_time.setText(shop.memo_time);

                    edit_mypage_name.setText(shop.name);
                    edit_mypage_phone.setText(shop.phone);
                    edit_mypage_email.setText(shop.email);
                    edit_mypage_pw.setText(shop.pw);

                    //추후에 더 추가

                    String[] info = {shop.getEmail(), shop.getName(), shop.getName(), shop.getId(), shop.getPw(),shop.getMemo(),shop.getMemo_date(),shop.getMemo_shop(), shop.getMemo_time()};//ArrayStar 제외
                    String Result = info[0] + " " + info[1] + " " + info[2] + " " + info[3] + " " + info[4] + " " + info[5] + " " + info[6] + " " + info[7] + " " + info[8];
                    Log.d("getFirebaseDatabaseShop", "info : " + Result);

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


        if (class_name.equals("users")){
            getFirebaseDatabase();
        } else if (class_name.equals("shops")){
            getFirebaseDatabaseShop();
        }
        getFirebaseDatabaseStar();


        Log.d("DEBUG_mypage", "info : " + memo_date);

        if (class_name.equals("users")){

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
                    getFirebaseDatabase();

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
                    getFirebaseDatabase();

                }


            });


        } else if (class_name.equals("shops")){

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

                    addshop(true);
                    getFirebaseDatabaseShop();

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

                    addshop(true);
                    getFirebaseDatabaseShop();

                }


            });

            btn_mypage_creator.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(act5_mypage.this, act8_shopdetaill_creator.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }

            });


        }




    }



}
