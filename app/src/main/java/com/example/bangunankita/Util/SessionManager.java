package com.example.bangunankita.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.bangunankita.Login;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final  String KEY_USERNAME = "username";
    public static final  String KEY_NAMA = "nama";
    public static final  String KEY_TOKEN = "accessToken";
    public static final  String KEY_ID = "id";
    private static final  String is_login = "loginstatus";
    private static final  String SHARE_NAME = "loginsession";
    private static final  int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager (Context context){
        this._context = context;
        sp= _context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE);
        editor = sp.edit();

        }
        public void storeLogin(String id,String username,String nama,String accessToken){

        editor.putBoolean(is_login,true);
            editor.putString(KEY_ID,id);
            editor.putString(KEY_USERNAME,username);
            editor.putString(KEY_NAMA,nama);
            editor.putString(KEY_TOKEN,accessToken);
            editor.commit();
        }
        public HashMap<String, String> getDetailLogin(){
            HashMap<String,String> map = new HashMap<>();
            map.put(KEY_ID,sp.getString(KEY_ID,null));
            map.put(KEY_NAMA,sp.getString(KEY_NAMA,null));
            map.put(KEY_USERNAME,sp.getString(KEY_USERNAME,null));
            map.put(KEY_TOKEN,sp.getString(KEY_TOKEN,null));

            return map;
    }



        public void checkLogin(){
        if (!this.Loggin()){
            Intent login = new Intent(_context, Login.class);
            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            _context.startActivity(login);
        }

        }
        public void logout(){
        editor.clear();
        editor.commit();
        }
        public Boolean Loggin(){
        return sp.getBoolean(is_login,false);

        }

    }

