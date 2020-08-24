package com.example.bangunankita;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Settingprofile extends AppCompatActivity {
  private  EditText Nama,email,notelp,passlama,passbaru,username2,repeatpass;
  private TextInputLayout inputpass1,inputpass2,inputpass3;
  private LinearLayout lay;
  private Button edit,gantipass,editpw;
    private String token,status,picture1,stringid,name,username,email1,notelp1;
    private int Idpengembang = 0;
    ProgressDialog pd;
    SessionManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingprofile);
        sm= new SessionManager(Settingprofile.this);
        edit = findViewById(R.id.editbtn1);
        gantipass = findViewById(R.id.gantipass);
        Nama = findViewById(R.id.nama);
        username2 = findViewById(R.id.username);
        email = findViewById(R.id.email);
        editpw = findViewById(R.id.editbtn);
        notelp = findViewById(R.id.notelp);
        passlama= findViewById(R.id.passlama);
        passbaru = findViewById(R.id.passwordbaru);
        repeatpass = findViewById(R.id.repeatpass);
        HashMap<String,String> map = sm.getDetailLogin();
        token=(map.get(sm.KEY_TOKEN));
        status=(map.get(sm.KEY_STATUS));
        picture1=(map.get(sm.KEY_PICTURE));
        stringid=(map.get(sm.KEY_ID));
        name= (map.get(sm.KEY_NAMA));
        username= (map.get(sm.KEY_USERNAME));
        email1= (map.get(sm.KEY_EMAIL));
        notelp1= (map.get(sm.KEY_NOTELP));
        lay = findViewById(R.id.lay);
        inputpass1 = findViewById(R.id.inputpass1);
        inputpass2 = findViewById(R.id.inputpass2);
        inputpass3 = findViewById(R.id.inputpass3);
        Nama.setText(name);
        username2.setText(username);
        email.setText(email1);
        notelp.setText(notelp1);
        pd = new ProgressDialog(Settingprofile.this);
        sm.checkLogin();

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apiKey = "oa00000000app";
                if (!TextUtils.isEmpty(stringid) && TextUtils.isDigitsOnly(stringid)) {
                    Idpengembang= Integer.parseInt(stringid);
                } else {
                    Idpengembang =0;
                }
                HashMap<String, String> map = new HashMap<>();

                map.put("nama", Nama.getText().toString());
                map.put("email", email.getText().toString());
                map.put("notelp", notelp.getText().toString());
                map.put("username", username2.getText().toString());
                pd.setMessage("loading");
                pd.show();

                Call<Void> call = ApiClient.getRequestInterface().actionUpdateprofile(Idpengembang,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            pd.hide();
                            Toast.makeText(Settingprofile.this,
                                    "Edit Data User Berhasil dirubah",
                                    Toast.LENGTH_LONG).show();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Settingprofile.this);
                            builder1.setTitle("Peringatan");
                            builder1.setMessage("Profile anda berhasil di update silahkan logout dan login kembali untuk melihat perubahan!!");
                            builder1.setIcon(R.drawable.ic_warning_red_24dp);
                            builder1.setCancelable(false);


                            builder1.setPositiveButton(
                                    "Log Out",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            sm.logout();
                                            sm.checkLogin();
                                        }
                                    });

                            AlertDialog alert11 = builder1.create();
                            alert11.show();
//                            Intent Dashboard = new Intent(Settingprofile.this, Dashboard.class);
//                            startActivity(Dashboard);

                        } else if (response.code() == 422) {
                            Toast.makeText(Settingprofile.this,
                                    "Something Wrong",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Settingprofile.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        gantipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lay.setVisibility(View.VISIBLE);
                inputpass1.setVisibility(View.VISIBLE);
                inputpass2.setVisibility(View.VISIBLE);
                inputpass3.setVisibility(View.VISIBLE);
            }

        });
        editpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initvalidation();
                compare();
                String apiKey = "oa00000000app";
                if (!TextUtils.isEmpty(stringid) && TextUtils.isDigitsOnly(stringid)) {
                    Idpengembang= Integer.parseInt(stringid);
                } else {
                    Idpengembang =0;
                }
                HashMap<String, String> map = new HashMap<>();

                map.put("oldpassword", passlama.getText().toString());
                map.put("newpassword", passbaru.getText().toString());
                map.put("repeatpassword", repeatpass.getText().toString());
//                map.put("username", username2.getText().toString());


                Call<Void> call = ApiClient.getRequestInterface().actionUpdatepassword(Idpengembang,apiKey,token,map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(Settingprofile.this,
                                    "Password berhasil dirubah",
                                    Toast.LENGTH_LONG).show();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Settingprofile.this);
                            builder1.setTitle("Peringatan");
                            builder1.setMessage("Profile anda berhasil di update silahkan logout dan login kembali untuk melihat perubahan!!");
                            builder1.setIcon(R.drawable.ic_warning_red_24dp);
                            builder1.setCancelable(false);

                            builder1.setPositiveButton(
                                    "Log Out",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            sm.logout();
                                            sm.checkLogin();
                                        }
                                    });

                            AlertDialog alert11 = builder1.create();
                            alert11.show();
//                            Intent Dashboard = new Intent(Settingprofile.this, Dashboard.class);
//                            startActivity(Dashboard);

                        } else if (response.code() == 422) {
                            Toast.makeText(Settingprofile.this,
                                    "Terjadi kesalahan silahkan cek password lama atau password baru tidak sama",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Settingprofile.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void initvalidation() {
        if (passlama.getText().toString().length() == 0){
            passlama.setError("field ini harus diisi!");
        }else if (passbaru.getText().toString().length() == 0){
            passbaru.setError("field ini harus diisi!");
        }else if (repeatpass.getText().toString().length() == 0){
            repeatpass.setError("field ini harus diisi!");
        }
    }
    private void compare(){
        if (passbaru.getText().toString().length() != repeatpass.getText().toString().length()){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Settingprofile.this);
            builder1.setTitle("Peringatan");
            builder1.setMessage("password baru dan repeat password tidak sama silahkan ulangi lagi");
            builder1.setIcon(R.drawable.ic_warning_red_24dp);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }else {

        }
    }

}
