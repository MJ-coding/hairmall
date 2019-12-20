package com.example.hairmall2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.hairmall2.ui.act11_stylesearchingm;
import com.example.hairmall2.ui.act3_login;
import com.example.hairmall2.ui.act5_mypage;
import com.example.hairmall2.ui.act8_shopdetail;
import com.example.hairmall2.ui.slideshow.act11_stylesearching;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainPage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private EditText edit_test;
    private Button btn_test;
    private TextView text_test;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private String text;

    private String id;

    private String class_name;

    private View header;

    /*
        public String getId() {
            return id;
        }
    */

    public void goto_login(View v){
        Intent Act = new Intent (getApplicationContext(), act3_login.class);
        startActivity(Act);
    }

    public void goto_mypage(View v){
        Intent Act = new Intent(getApplicationContext(), act5_mypage.class);
        Act.putExtra("id", id);
        Act.putExtra("class_name", class_name);
        startActivity(Act);
    }

    public void goto_shopdetail(View v){
        Intent Act = new Intent(getApplicationContext(), act8_shopdetail.class);
        startActivity(Act);
    }

    public void goto_stylesearchingm(View v){
        Intent Act = new Intent(getApplicationContext(), act11_stylesearchingm.class);
        Act.putExtra("id", id);
        Act.putExtra("class_name", class_name);
        startActivity(Act);
    }

    public void goto_stylesearchingw(View v){
        Intent Act = new Intent(getApplicationContext(), act11_stylesearching.class);
        startActivity(Act);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act2_menubar);

        //네비게이션 바 설정 부분
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        edit_test = findViewById(R.id.edit_test);
        btn_test = findViewById(R.id.btn_test);
        text_test = findViewById(R.id.text_test);

        Intent intent=new Intent(this.getIntent()); // 로그인부터시작 안하면 터짐 act1_mainpage도.
        id=intent.getStringExtra("id");
        class_name = intent.getStringExtra("class_name");

        /* // 변수접근 현재 안됨
        header = getLayoutInflater().inflate(R.layout.nav_header_number2, null, false);
        textView = header.findViewById(R.id.textView);
        textView.setText(id+"님 환영합니다!");

        LinearLayout container = (LinearLayout) findViewById(R.id.container);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.nav_header_number2, container, true );
        textView2 = view.findViewById(R.id.textView);
        textView2.setText(id+"님 환영합니다!");
        */



        Log.d("DEBUG_mainpage",id);
        Log.d("DEBUG_mainpage",class_name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.number2, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



}
