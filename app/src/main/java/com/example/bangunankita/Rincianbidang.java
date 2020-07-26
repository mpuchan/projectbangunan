package com.example.bangunankita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bangunankita.Model.Perhitunganbidang1;
import com.example.bangunankita.Model.ResponseBidang;
import com.example.bangunankita.Retrovit.ApiClient;
import com.example.bangunankita.Util.SessionManager;
import com.example.bangunankita.adapter.Bidang_adapter;
import com.example.bangunankita.adapter.Reportbidang;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Rincianbidang extends AppCompatActivity implements View.OnClickListener {
    ImageView print;
    int ProyekID;
    String Ju;
    SessionManager sm;
    String token;
    String stringid;
    String nama;
    private Reportbidang reportbidang;
    private List<Perhitunganbidang1> BidangModel1 = new ArrayList<>();
    private RecyclerView mRecyclerView1;
    Context mContext;
    int totalPrice = 0;
    String mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincianbidang);
        sm = new SessionManager(Rincianbidang.this);
        mRecyclerView1 = findViewById(R.id.tbbidang);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        HashMap<String, String> map = sm.getDetailLogin();
        token = (map.get(sm.KEY_TOKEN));
        stringid = (map.get(sm.KEY_ID));
        print = findViewById(R.id.print);
        print.setOnClickListener(this);
        sm.checkLogin();
        Bundle bundle = getIntent().getExtras();
        mId = bundle.getString("idproyek1");

        Toast.makeText(Rincianbidang.this, "Proyek Id" + mId,
                Toast.LENGTH_SHORT).show();
        Ju = mId;
        mContext = this;
        getperhitunganbidang1();
    }

    @Override
    public void onClick(View v) {

        Workbook wb=new HSSFWorkbook();
        Cell cell=null;
        CellStyle cellStyle=wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        //Now we are creating sheet
        Sheet sheet=null;
        sheet = wb.createSheet("Perhitungan Bidang");
        //Now column and row
        Row row =sheet.createRow(0);


        cell=row.createCell(0);
        cell.setCellValue("Name");
        cell.setCellStyle(cellStyle);

        cell=row.createCell(1);
        cell.setCellValue("Number");
        cell.setCellStyle(cellStyle);

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*200));

        File file = new File(getExternalFilesDir(null),"Report Bidang.xls");
        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(file);
            wb.write(outputStream);
            Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(),"NO OK",Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }

    private void getperhitunganbidang1() {
        String apiKey = "oa00000000app";
        if (!TextUtils.isEmpty(Ju) && TextUtils.isDigitsOnly(Ju)) {
            ProyekID = Integer.parseInt(Ju);
        } else {
            ProyekID = 0;
        }

        Call<ResponseBidang> call = ApiClient.getRequestInterface().getPerhitunganbidang(ProyekID, apiKey, token);
        call.enqueue(new Callback<ResponseBidang>() {
            @Override
            public void onResponse(Call<ResponseBidang> call, Response<ResponseBidang> response) {
//                swipeRefreshLayout.setRefreshing(false);
//                String message = response.body().getMessage();
                if (response.code() == 200) {
                    BidangModel1 = response.body().getPerhitunganbidang();
                    final ArrayList<String> list = new ArrayList<String>();
                    for (int i = 0; i < BidangModel1.size(); ++i) {
                        list.add(String.valueOf(BidangModel1.get(i).getNama()));
                    }
//                    final Reportbidang adapter = new Reportbidang(this,
//                            android.R.layout.simple_list_item_1, list);
//                    listview.setAdapter(adapter);
                    reportbidang = new Reportbidang(Rincianbidang.this, BidangModel1, list);
                    mRecyclerView1.setAdapter(reportbidang);

//                    for (int i = 0; i<BidangModel1.size(); i++)
//                    {
//                        totalPrice += BidangModel1.get(i).getHargatotal();
//                    }
//                    totalharga.setText("Rp."+String.valueOf(totalPrice));


                } else if (response.code() == 422) {
                    Toast.makeText(Rincianbidang.this, "Something wrong!",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBidang> call, Throwable t) {
//                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Rincianbidang.this, "Oops! Something went wrong!" + t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}
