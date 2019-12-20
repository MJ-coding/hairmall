package com.example.hairmall2.ui.gallery;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hairmall2.R;
import com.example.hairmall2.ui.act8_shopdetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class act6_shopsearching extends Fragment implements View.OnClickListener{

    private act6_shopsearchingviewmodel galleryViewModel;
    private String id;
    private String class_name;
    private String shop_id;

    private String id_star;

    private ImageView imag_searching_star1;
    private ImageView imag_searching_star2;
    private ImageView imag_searching_star3;
    private ImageView imag_searching_star4;


    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-test-7d2fa.firebaseio.com");
    DatabaseReference childRef;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(act6_shopsearchingviewmodel.class);
        View root = inflater.inflate(R.layout.act6_shopsearching, container, false);

        final TextView textView = root.findViewById(R.id.text_gallery);
        imag_searching_star1 = root.findViewById(R.id.imag_searching_star1);
        imag_searching_star2 = root.findViewById(R.id.imag_searching_star2);
        imag_searching_star3 = root.findViewById(R.id.imag_searching_star3);
        imag_searching_star4 = root.findViewById(R.id.imag_searching_star4);

        id=getActivity().getIntent().getExtras().getString("id"); // 로그인부터 시작 안하면 intent에러
        class_name=getActivity().getIntent().getExtras().getString("class_name");

        Log.d("shopsearching","id "+id);
        Log.d("shopsearching","class_name "+class_name);

        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        //여기서 부터 해당 미용실actvity로 이동구현
        ImageView fir_imageView = root.findViewById(R.id.shop_image_fir);
        ImageView sec_imageView = root.findViewById(R.id.shop_image2);
        ImageView thr_imageView = root.findViewById(R.id.shop_image3);
        ImageView for_imageView = root.findViewById(R.id.shop_image4);
        Button mapbtn = root.findViewById(R.id.mapbtn);


        fir_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent(지금 data,넘어갈 activity)
                shop_id = "xino";
                Intent intent = new Intent(getContext(), act8_shopdetail.class);
                intent.putExtra("shop_name","지노헤어");
                intent.putExtra("gpa","3.4");
                intent.putExtra("user_id", id);
                intent.putExtra("shop_id", shop_id);
                intent.putExtra("class_name", class_name);
                startActivity(intent);
            }
        });
        sec_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), com.example.hairmall2.ui.act8_shopdetail2.class);
               //인텐트에 data입력해주기
                intent.putExtra("shop_name","리안헤어");
                intent.putExtra("gpa","4.3");
                intent.putExtra("user_id", id);
                intent.putExtra("shop_id", "riahn");
                intent.putExtra("class_name", class_name);
                startActivity(intent);
            }
        });
        thr_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), com.example.hairmall2.ui.act8_shopdetail3.class);
                intent.putExtra("shop_name","이철 헤어커커");
                intent.putExtra("gpa","3.9");
                intent.putExtra("user_id", id);
                intent.putExtra("shop_id", "leechul");
                intent.putExtra("class_name", class_name);
                startActivity(intent);
            }
        });
        for_imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), com.example.hairmall2.ui.act8_shopdetail4.class);
                intent.putExtra("shop_name","박승철 헤어");
                intent.putExtra("gpa","3.9");
                intent.putExtra("user_id", id);
                intent.putExtra("shop_id", "pschair");
                intent.putExtra("class_name", class_name);
                startActivity(intent);
            }
        });

        mapbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mapintent = new Intent();
                ComponentName mapcomponentName = new ComponentName(
                        "com.example.hairmall2",
                        "com.example.hairmall2.MapsActivity");

                mapintent.setComponent(mapcomponentName);
                startActivity(mapintent);

            }
        });


        return  root;

        }

    public void getFirebaseDatabase(){

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

                        if (key.equals("xino")) {
                            Log.d("shopdetail", "keykey " + key);
                            if (star.equals("star")) {
                                imag_searching_star1.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.star, null));
                                Log.d("shopdetail", "starstar " + star);
                            } else {
                                imag_searching_star1.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.emptystar3, null));
                            }
                        } else if(key.equals("riahn")){
                            Log.d("shopdetail","keykey "+key);
                            if (star.equals("star")) {
                                imag_searching_star2.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.star, null));
                                Log.d("shopdetail","starstar "+star);
                            } else {
                                imag_searching_star2.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.emptystar3, null));
                            }
                        }  else if(key.equals("leechul")){
                            Log.d("shopdetail","keykey "+key);
                            if (star.equals("star")) {
                                imag_searching_star3.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.star, null));
                                Log.d("shopdetail","starstar "+star);
                            } else {
                                imag_searching_star3.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.emptystar3, null));
                            }
                        }  else if(key.equals("pschair")){
                            Log.d("shopdetail","keykey "+key);
                            if (star.equals("star")) {
                                imag_searching_star4.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.star, null));
                                Log.d("shopdetail","starstar "+star);
                            } else {
                                imag_searching_star4.setImageDrawable(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.emptystar3, null));
                            }
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

        id_star = id+"_star";

        getFirebaseDatabase();

    }

    @Override
    public void onClick(View v){

    }




}