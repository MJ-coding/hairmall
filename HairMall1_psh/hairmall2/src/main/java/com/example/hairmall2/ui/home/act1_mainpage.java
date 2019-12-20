package com.example.hairmall2.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hairmall2.KnowIndexOnClickListener;
import com.example.hairmall2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class act1_mainpage extends Fragment {

    private act6_mainpageviewmodel homeViewModel;

    private EditText edit_test;
    private Button btn_test;
    private TextView text_test;
    private String text;


    private String id;

    private String class_name;

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
    DatabaseReference childRef = mRootRef;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(act6_mainpageviewmodel.class);
        View root = inflater.inflate(R.layout.act1_mainpage, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        edit_test = root.findViewById(R.id.edit_test);
        btn_test = root.findViewById(R.id.btn_test);
        text_test = root.findViewById(R.id.text_test);

        edit_test.setVisibility(View.GONE);
        btn_test.setVisibility(View.GONE);
        text_test.setVisibility(View.GONE);

        id=getActivity().getIntent().getExtras().getString("id"); // 로그인부터 시작 안하면 intent에러
        class_name=getActivity().getIntent().getExtras().getString("class_name");
        Log.d("DEBUG2_act1_mainpage",id);
        Log.d("DEBUG2_act1_mainpage",class_name);

        // 메인 뷰 설정
        final Context context = getActivity();

        //리뷰 텍스트
        final TextView reviewText = root.findViewById(R.id.review_text);
        //오늘의 미용실 텍스트
        final TextView shopText = root.findViewById(R.id.shop_text);

        final ImageView[] reviewImage = new ImageView[3];
        final ImageView[] shopImage = new ImageView[3];


        reviewImage[0] = root.findViewById(R.id.recent_review1);
        reviewImage[1] = root.findViewById(R.id.recent_review2);
        reviewImage[2] = root.findViewById(R.id.recent_review3);

        shopImage[0] = root.findViewById(R.id.shop_image_fir);
        shopImage[1] = root.findViewById(R.id.shop_image_sec);
        shopImage[2] = root.findViewById(R.id.shop_image_thir);
        //최신 날짜기준으로 review 3개 가져오기

        final String[] testText = new String[3];
        testText[0]="1_등록된 리뷰가 없습니다.";
        testText[1]="2_등록된 리뷰가 없습니다.";
        testText[2]="3_등록된 리뷰가 없습니다.";

        final String[] info_Text = new String[3];
        info_Text[0]=" 아스카다 헤어 | 아스카다 헤어 디자인(숭실대점) ASKADA는 고객 특징을 분석하여 디자인하는 전문적인 " +
                "디자인 살롱으로서 고객에 맞는 어...";
        info_Text[1]="리안 헤어 | 기장추가나 숱추가 없이 브랜드 제품 정품사용. 저렴한 가격에 고퀄리티 헤어스타일을 완성해드립니다^^";
        info_Text[2]="이철 헤어커커 | 재방문율 1위!직원과 더불어 함께 성장하고 성공하는 것! 고객을 한결같이 마음으로 대하라!";
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("review").orderBy("date_day", Query.Direction.DESCENDING).limit(3).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //***task성공시 DB에 shop_name가져와서 작업하기
                            int count = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("result", document.getId() + " => " + document.getData().get("shop_name"));
                                String reviewTextString = "";
                                String grade_point_averge ="";
                                if (document.getData().get("review") != null && document.getData().get("gpa") != null) {
                                    reviewTextString = document.getData().get("review").toString();
                                    grade_point_averge= document.getData().get("gpa").toString();
                                }
                                else {
                                    reviewTextString = "";
                                    grade_point_averge="";
                                }
                                testText[count] = "평점:" + grade_point_averge +"리뷰:" +reviewTextString;
                                if (count == 0) {
                                    reviewText.setText(reviewTextString);
                                }
                                count += 1;
                            }
                        } else {
                            //task실패시 log남기

                            Log.w("result", "Error getting documents.", task.getException());
                        }
                    }
                });

        for (int i = 0; i < 3; i++) {
            reviewImage[i].setOnClickListener(new KnowIndexOnClickListener(i) {
                public void onClick(View v) {
                    reviewText.setText(testText[index]);
                    reviewImage[index].setPadding(4, 4, 4, 4);
                    reviewImage[index].setBackground(ContextCompat.getDrawable(context, R.drawable.borderline));

                    if (index == 0) {
                        reviewImage[1].setPadding(0, 0, 0, 0);
                        reviewImage[2].setPadding(0, 0, 0, 0);
                    } else if (index == 1) {
                        reviewImage[0].setPadding(0, 0, 0, 0);
                        reviewImage[2].setPadding(0, 0, 0, 0);
                    } else if (index == 2) {
                        reviewImage[0].setPadding(0, 0, 0, 0);
                        reviewImage[1].setPadding(0, 0, 0, 0);
                    }
                }
            });
        }

        for ( int i=0; i<3;i++){
        shopImage[i].setOnClickListener(new KnowIndexOnClickListener(i) {
            public void onClick(View v) {
                shopText.setText(info_Text[index]);
                shopImage[index].setPadding(4, 4, 4, 4);
                shopImage[index].setBackground(ContextCompat.getDrawable(context, R.drawable.borderline));


                if (index == 0) {
                    shopImage[1].setPadding(0, 0, 0, 0);
                    shopImage[2].setPadding(0, 0, 0, 0);
                } else if (index == 1) {
                    shopImage[0].setPadding(0, 0, 0, 0);
                    shopImage[2].setPadding(0, 0, 0, 0);
                } else if (index == 2) {
                    shopImage[0].setPadding(0, 0, 0, 0);
                    shopImage[1].setPadding(0, 0, 0, 0);
                }
            }
        });
    }


        return root;
    }

    public void getFirebaseDatabase(){
        childRef = mRootRef.child("test");
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String string = dataSnapshot.getValue(String.class);
                    text_test.setText(string);
                    Log.d("getFirebaseDatabaes","key : " + text);

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

        childRef = mRootRef.child("test");
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String string = dataSnapshot.getValue(String.class);
                    text_test.setText(string);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });

        btn_test.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_test:

                        text = edit_test.getText().toString();
                        childRef = mRootRef.child("test");
                        childRef.setValue(text);
                        getFirebaseDatabase();

                        btn_test.setText(id);
                }
            }

        });
    }
}