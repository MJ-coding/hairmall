package com.example.hairmall2;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class user1 {
    public String email;
    public String name;
    public String phone;
    public String id;
    public String pw;
    public String memo;
    public ArrayList<String> arrayStar = new ArrayList<>();

    public String memo_date;
    public String memo_shop;
    public String memo_time;

    public String class_name = "users";

    public user1 (){

    }

    public user1 (String email, String name, String phone, String id, String pw, String memo, ArrayList<String> arrayStar,
                  String memo_date, String memo_shop, String memo_time,
                  String class_name){
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.pw = pw;
        this.memo = memo;
        this.arrayStar = arrayStar;

        this.memo_date = memo_date;
        this.memo_shop = memo_shop;
        this.memo_time = memo_time;

        this.class_name = class_name;
    }

    @Exclude
    public String getMemo_date() {
        return memo_date;
    }

    public void setMemo_date(String memo_date) {
        this.memo_date = memo_date;
    }

    @Exclude
    public String getMemo_shop() {
        return memo_shop;
    }

    public void setMemo_shop(String memo_shop) {
        this.memo_shop = memo_shop;
    }

    @Exclude
    public String getMemo_time() {
        return memo_time;
    }

    public void setMemo_time(String memo_time) {
        this.memo_time = memo_time;
    }

    @Exclude
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Exclude
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Exclude
    public ArrayList<String> getArrayStar() {
        return arrayStar;
    }

    public void setArrayStar(ArrayList<String> arrayStar) {
        this.arrayStar = arrayStar;
    }

    @Exclude
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("name", name);
        result.put("phone", phone);
        result.put("id", id);
        result.put("pw", pw);
        result.put("class_name", class_name);
        return result;
    }

    @Exclude
    public Map<String, Object> setUser() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("name", name);
        result.put("phone", phone);
        result.put("pw", pw);
        return result;
    }

    @Exclude
    public Map<String, Object> setTime() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("memo_date", memo_date);
        result.put("memo_shop", memo_shop);
        result.put("memo_time", memo_time);
        return result;
    }

    @Exclude
    public Map<String, Object> toStar() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("arrayStar", arrayStar);
        return result;
    }
}
