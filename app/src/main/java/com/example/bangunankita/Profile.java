package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bangunankita.Util.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

import static maes.tech.intentanim.CustomIntent.customType;

public class Profile extends AppCompatActivity {
    FloatingActionButton keprofile,contact,logout;
    TextView isinama;
    SessionManager sm;
    Context mContext;
    private String token,status,picture1,stringid,name;
    ImageView picture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sm= new SessionManager(Profile.this);
        contact = findViewById(R.id.floatingAcdtionButton);
        isinama = findViewById(R.id.nama);
        logout = findViewById(R.id.floatingActionButton);
        picture = findViewById(R.id.picture);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        status=(map.get(sm.KEY_STATUS));
        picture1=(map.get(sm.KEY_PICTURE));
        stringid=(map.get(sm.KEY_ID));
        name= (map.get(sm.KEY_NAMA));
        isinama.setText(name);
        sm.checkLogin();
        keprofile = findViewById(R.id.keprofile);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.gravatar)
                .error(R.drawable.gravatar);
        Glide.with(this).load("https://1.bp.blogspot.com/"+picture1).apply(options).into(picture);
        keprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Settingprofile = (new Intent(Profile.this, Settingprofile.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                startActivity(Settingprofile);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               hubungi();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Profile.this);
                builder1.setTitle("Logout Aplikasi");
                builder1.setMessage("Yakin Ingin Logout?");
                builder1.setIcon(R.drawable.ic_warning_red_24dp);
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sm.logout();
                                sm.checkLogin();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
    }


        @Override
        public void onBackPressed() {
            super.onBackPressed();
            customType(Profile.this,"up-to-bottom");

        }


    private void hubungi() {
        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_VIEW);
            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");
            waIntent.setData(Uri.parse("http://api.whatsapp.com/send?phone=+6287754718671"));
                    startActivity(waIntent);

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }

}
