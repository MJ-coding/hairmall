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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class act8_shopdetail extends AppCompatActivity implements View.OnClickListener{

    private String user_id;
    private String class_name;
    private String shop_id;
    private String id_star;
    private String id;


    private Button btn_creator_star;

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-test-7d2fa.firebaseio.com");
    DatabaseReference childRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act8_shopdetail);

        Intent intent=new Intent(this.getIntent()); // shopsearching으로부터 받아옴
        user_id=intent.getStringExtra("user_id");
        shop_id=intent.getStringExtra("shop_id");
        class_name=intent.getStringExtra("class_name");

        btn_creator_star = findViewById(R.id.btn_creator_star);
        btn_creator_star.setOnClickListener(this);

        Log.d("shopdetail","id "+user_id);
        Log.d("shopdetail","class_name "+class_name);
        Log.d("shopdetail","shop_id "+shop_id);



        //리뷰작성버튼
        Button button =(Button)findViewById(R.id.write_review);
        Button call_button =(Button)findViewById(R.id.call);
        Button chat_butt=(Button)findViewById(R.id.message);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), act10_review.class);
                //act6_shopsearching에서 설정해준 이름data 가져오기
                String shop_name = getIntent().getStringExtra("shop_name");
                String gpa =getIntent().getStringExtra("gpa");
                //다시 다음 activity로 넘기기위해 intent data입력하기
                intent.putExtra("shop_name",shop_name);
                intent.putExtra("gpa",gpa);
                startActivity(intent);

            }
        });

        call_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String tel ="tel:021111111";
                startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));

            }
        });

        chat_butt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), act9_privatetalk.class);
                startActivity(intent);

            }
        });
        //메뉴누르면 메뉴크게 보기
        ImageView menu_imview =(ImageView)findViewById(R.id.menu_fir);

        menu_imview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent(getApplicationContext(), act8_shop1_menu.class);
                startActivity(intent);
            }
        });
    }



    public void getFirebaseDatabase(){

        id = user_id;
        id_star = id+"_star";

        Log.d("shopdetail","id "+id);

        id_star = id+"_star";
        childRef = mRootRef.child("hairmall").child(class_name).child(id).child(id_star);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        String key = ds.getKey();
                        String star = ds.getValue(String.class);

                        Log.d("shopdetail","key "+key);
                        Log.d("shopdetail","star "+star);

                        if (key.equals(shop_id)){
                            Log.d("shopdetail","keykey "+key);
                            if (star.equals("star")) {
                                btn_creator_star.setText("★");
                                //realstar = btn_creator_star.getText().toString();
                                Log.d("shopdetail","starstar "+star);
                            }
                            break;
                        }

                    }

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

        id = user_id;
        id_star = id+"_star";

       // realstar = btn_creator_star.getText().toString();

        getFirebaseDatabase();

    }

    @Override
    public void onClick(View v){



        switch (v.getId()){
            case R.id.btn_creator_star:
                String realstar = btn_creator_star.getText().toString();
                if (realstar.equals("☆")){
                    //String realstar = btn_creator_star.getText().toString();
                    childRef = mRootRef.child("hairmall").child(class_name).child(id).child(id_star).child(shop_id);
                    childRef.setValue("star");
                    btn_creator_star.setText("★");
                } else if(realstar.equals("★")){

                    childRef = mRootRef.child("hairmall").child(class_name).child(id).child(id_star).child(shop_id);
                    childRef.setValue("");
                    btn_creator_star.setText("☆");
                }


        }


    }





}
