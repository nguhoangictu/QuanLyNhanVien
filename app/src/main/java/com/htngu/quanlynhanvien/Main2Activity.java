package com.htngu.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private ImageView img;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt = findViewById(R.id.textView);
        img = findViewById(R.id.img_sex);
        Bundle bundle = getIntent().getBundleExtra("data");
        txt.setText(bundle.getString("maNV")+" - "+bundle.getString("tenNV"));
        if (bundle.getString("gioiTinh").equals("Nam")) img.setBackgroundResource(R.drawable.male);
        else img.setBackgroundResource(R.drawable.female);
    }
}
