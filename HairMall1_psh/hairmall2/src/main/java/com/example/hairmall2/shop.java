package com.example.hairmall2;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class shop {
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

    public String class_name = "shops";

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


    public shop (){

    }

    public shop (String email, String name, String phone, String id, String pw,
                 String memo, ArrayList<String> arrayStar, String memo_date, String memo_shop, String memo_time,
                 String class_name,
                 String shop_main_url, String shop_imag_url1, String shop_imag_url2, String shop_imag_url3,
                 String shop_menu_url1, String shop_menu_url2, String shop_menu_url3, String shop_menu_url4,
                 String shop_review_url1, String shop_review_url2, String shop_review_url3, String shop_review_url4,
                 String reputation,
                 ArrayList<String> arrayReserve, ArrayList<String> arrayReview){
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

        this.shop_main_url = shop_main_url;
        this.shop_imag_url1 = shop_imag_url1;
        this.shop_imag_url2 = shop_imag_url2;
        this.shop_imag_url3 = shop_imag_url3;

        this.shop_menu_url1 = shop_menu_url1;
        this.shop_menu_url2 = shop_menu_url2;
        this.shop_menu_url3 = shop_menu_url3;
        this.shop_menu_url4 = shop_menu_url4;

        this.shop_review_url1 = shop_review_url1;
        this.shop_review_url2 = shop_review_url2;
        this.shop_review_url3 = shop_review_url3;
        this.shop_review_url4 = shop_review_url4;

        this.reputation = reputation;

        this.arrayReserve = arrayReserve;
        this.arrayReview = arrayReview;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("email", email);
        result.put("name", name);
        result.put("phone", phone);
        result.put("id", id);
        result.put("pw", pw);

        result.put("memo_date", memo_date);
        result.put("memo_shop", memo_shop);
        result.put("memo_time", memo_time);

        result.put("class_name", class_name);

        result.put("shop_main_url", shop_main_url);
        result.put("shop_imag_url1", shop_imag_url1);
        result.put("shop_imag_url2", shop_imag_url2);
        result.put("shop_imag_url3", shop_imag_url3);

        result.put("shop_menu_url1", shop_menu_url1);
        result.put("shop_menu_url2", shop_menu_url2);
        result.put("shop_menu_url3", shop_menu_url3);
        result.put("shop_menu_url4", shop_menu_url4);

        result.put("shop_review_url1", shop_review_url1);
        result.put("shop_review_url2", shop_review_url2);
        result.put("shop_review_url3", shop_review_url3);
        result.put("shop_review_url4", shop_review_url4);

        result.put("reputation", reputation);

        return result;
    }

    @Exclude
    public Map<String, Object> toMapUri() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("shop_imag_url1", shop_imag_url1);
        result.put("shop_imag_url2", shop_imag_url2);
        result.put("shop_imag_url3", shop_imag_url3);

        result.put("shop_main_url", shop_main_url);

        result.put("shop_menu_url1", shop_menu_url1);
        result.put("shop_menu_url2", shop_menu_url2);
        result.put("shop_menu_url3", shop_menu_url3);
        result.put("shop_menu_url4", shop_menu_url4);

        result.put("shop_review_url1", shop_review_url1);
        result.put("shop_review_url2", shop_review_url2);
        result.put("shop_review_url3", shop_review_url3);
        result.put("shop_review_url4", shop_review_url4);


        return result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public ArrayList<String> getArrayStar() {
        return arrayStar;
    }

    public void setArrayStar(ArrayList<String> arrayStar) {
        this.arrayStar = arrayStar;
    }

    public String getMemo_date() {
        return memo_date;
    }

    public void setMemo_date(String memo_date) {
        this.memo_date = memo_date;
    }

    public String getMemo_shop() {
        return memo_shop;
    }

    public void setMemo_shop(String memo_shop) {
        this.memo_shop = memo_shop;
    }

    public String getMemo_time() {
        return memo_time;
    }

    public void setMemo_time(String memo_time) {
        this.memo_time = memo_time;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getShop_main_url() {
        return shop_main_url;
    }

    public void setShop_main_url(String shop_main_url) {
        this.shop_main_url = shop_main_url;
    }

    public String getShop_imag_url1() {
        return shop_imag_url1;
    }

    public void setShop_imag_url1(String shop_imag_url1) {
        this.shop_imag_url1 = shop_imag_url1;
    }

    public String getShop_imag_url2() {
        return shop_imag_url2;
    }

    public void setShop_imag_url2(String shop_imag_url2) {
        this.shop_imag_url2 = shop_imag_url2;
    }

    public String getShop_imag_url3() {
        return shop_imag_url3;
    }

    public void setShop_imag_url3(String shop_imag_url3) {
        this.shop_imag_url3 = shop_imag_url3;
    }

    public String getShop_menu_url1() {
        return shop_menu_url1;
    }

    public void setShop_menu_url1(String shop_menu_url1) {
        this.shop_menu_url1 = shop_menu_url1;
    }

    public String getShop_menu_url2() {
        return shop_menu_url2;
    }

    public void setShop_menu_url2(String shop_menu_url2) {
        this.shop_menu_url2 = shop_menu_url2;
    }

    public String getShop_menu_url3() {
        return shop_menu_url3;
    }

    public void setShop_menu_url3(String shop_menu_url3) {
        this.shop_menu_url3 = shop_menu_url3;
    }

    public String getShop_menu_url4() {
        return shop_menu_url4;
    }

    public void setShop_menu_url4(String shop_menu_url4) {
        this.shop_menu_url4 = shop_menu_url4;
    }

    public String getShop_review_url1() {
        return shop_review_url1;
    }

    public void setShop_review_url1(String shop_review_url1) {
        this.shop_review_url1 = shop_review_url1;
    }

    public String getShop_review_url2() {
        return shop_review_url2;
    }

    public void setShop_review_url2(String shop_review_url2) {
        this.shop_review_url2 = shop_review_url2;
    }

    public String getShop_review_url3() {
        return shop_review_url3;
    }

    public void setShop_review_url3(String shop_review_url3) {
        this.shop_review_url3 = shop_review_url3;
    }

    public String getShop_review_url4() {
        return shop_review_url4;
    }

    public void setShop_review_url4(String shop_review_url4) {
        this.shop_review_url4 = shop_review_url4;
    }

    public String getReputation() {
        return reputation;
    }

    public void setReputation(String reputation) {
        this.reputation = reputation;
    }

    public ArrayList<String> getArrayReserve() {
        return arrayReserve;
    }

    public void setArrayReserve(ArrayList<String> arrayReserve) {
        this.arrayReserve = arrayReserve;
    }

    public ArrayList<String> getArrayReview() {
        return arrayReview;
    }

    public void setArrayReview(ArrayList<String> arrayReview) {
        this.arrayReview = arrayReview;
    }
}
