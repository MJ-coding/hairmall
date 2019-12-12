package com.example.hairmall2.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hairmall2.R;
import com.example.hairmall2.ui.act8_shopdetail;

public class act6_shopsearching extends Fragment {

    private act6_shopsearchingviewmodel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(act6_shopsearchingviewmodel.class);
        View root = inflater.inflate(R.layout.act6_shopsearching, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        //여기서 부터 해당 미용실actvity로 이동구현
        ImageView imageView = root.findViewById(R.id.shop_image1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent(지금 data,넘어갈 activity)
                Intent intent = new Intent(getContext(), act8_shopdetail.class);
                intent.putExtra("shop_name","주노헤어");
                startActivity(intent);
            }
        });

        return  root;

        }




}