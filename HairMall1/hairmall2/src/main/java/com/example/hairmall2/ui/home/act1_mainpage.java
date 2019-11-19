package com.example.hairmall2.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class act1_mainpage extends Fragment {

    private act6_mainpageviewmodel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(act6_mainpageviewmodel.class);
        View root = inflater.inflate(R.layout.act1_mainpage, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        // 메인 뷰 설정
        final Context context = getActivity();

        //리뷰 텍스트
        final TextView reviewText = root.findViewById(R.id.review_text);

        final ImageView[] reviewImage = new ImageView[3];

        reviewImage[0] = root.findViewById(R.id.recent_review1);
        reviewImage[1] = root.findViewById(R.id.recent_review2);
        reviewImage[2] = root.findViewById(R.id.recent_review3);

        final String[] testText = new String[3];
        testText[0]="1번 리뷰";
        testText[1]="2번 리뷰";
        testText[2]="3번 리뷰";

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
        return root;
    }

    public void setFirebase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("test", "DocumentSnapshot added with ID: " +
                                documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("test", "Error adding document", e);
                    }
                });
    }

}