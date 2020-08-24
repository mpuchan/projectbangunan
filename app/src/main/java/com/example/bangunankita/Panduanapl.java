package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Panduanapl extends Activity {
    private TextView judul,judl1;
    private ImageView image;
    private String inisial;
    int image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduanapl);
        judul = findViewById(R.id.judulpanduan);
        image = findViewById(R.id.thisimage);
        judl1 = findViewById(R.id.judulpanduan2);
        Bundle bundle = getIntent().getExtras();
        judul.setText(bundle.getString("ini"));
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image.setImageBitmap(bmp);

        judl1.setText(bundle.getString("deskripsi"));


    }

}
