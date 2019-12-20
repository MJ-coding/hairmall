package com.example.hairmall2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hairmall2.ui.act8_shopdetaill_creator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class act5_mypage_shopAdapter extends RecyclerView.Adapter<act5_mypage_shopAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<user1> list_user1;
    private ArrayList<shop> list_shop;
    public ArrayList<String> list_string;
    private String shop_id;
    private String shop_id_url;
    private String url;
    public int pos;


    /*

        public void getFirebaseDatabaseShop_uri(){
        childRef = mRootRef.child("hairmall").child("shops").child(shop_id).child(shop_id_url);
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                     url = dataSnapshot.getValue(String.class);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Tag", "Failed to read value.", databaseError.toException());
            }
        });

    }

     */

    public OnItemClickListener mOnitemClickListener = null;


    public interface OnItemClickListener {
        void onItemClick(View view, String s);
    }

    public void setOnitemClickListener(OnItemClickListener listener){
        mOnitemClickListener = listener;
    }

    public act5_mypage_shopAdapter(Context mContext, ArrayList<String> list_string) {
        this.mContext = mContext;
        this.list_string = list_string;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Log.e("RecyclerView _create::", "A");
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.act5_mypage_item_shop, parent, false);
        return new ViewHolder(convertView);
    }

/////



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final String str = list_string.get(position);
        final String[] nowData = str.split("\\s+");

        shop_id = nowData[0];
        shop_id_url = nowData[1];
        Log.e("RecyclerView_binder ::", "a");

        //String uri ="ABCD";

        Glide.with(mContext).load(shop_id_url).into(holder.img_mypage_item_shop);
        //holder.img_mypage_item_shop.setVisibility(View.GONE);
        holder.text_mypage_item_shop.setText(shop_id);
        holder.layout_mypage_item_shop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if (position != RecyclerView.NO_POSITION){
                    pos = position;
                }
                mOnitemClickListener.onItemClick(v, str);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_string.size();
    }

    public void add(String s){
        list_string.add(s);
    }

    public void clearAddAll(ArrayList<String> strs){
        list_string.clear();
        list_string=strs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout layout_mypage_item_shop;
        private ImageView img_mypage_item_shop;
        private TextView text_mypage_item_shop;

        public ViewHolder(View convertView){
            super(convertView);

            layout_mypage_item_shop = convertView.findViewById(R.id.layout_mypage_item_shop);
            img_mypage_item_shop = convertView.findViewById(R.id.img_mypage_item_shop);
            text_mypage_item_shop = convertView.findViewById(R.id.text_mypage_item_shop);

        }
    }

}
