package com.htngu.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.htngu.quanlynhanvien.database.DBManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private EditText edtMa, edtName;
    private RadioGroup ragSex;
    private ListView lv;
    private NhanVienAdapter adapter;
    private ArrayList<NhanVien> nhanViens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("maNV", nhanViens.get(i).getMaNV());
                bundle.putString("tenNV", nhanViens.get(i).getTenNV());
                bundle.putString("gioiTinh", nhanViens.get(i).getGioiTinh());
                intent.putExtra("data", bundle);
                startActivity(intent);

            }
        });
    }

    private void init() {
        dbManager = new DBManager(getApplicationContext());
        edtMa = findViewById(R.id.edt_ma);
        edtName = findViewById(R.id.edt_name);
        ragSex = findViewById(R.id.rag_sex);
        lv = findViewById(R.id.lv_nv);
        nhanViens = dbManager.getAllStudent();

        adapter = new NhanVienAdapter(getApplicationContext(), nhanViens);
        lv.setAdapter(adapter);
    }

    public void addNV(View view) {
        if (edtName.getText().toString().isEmpty()||edtMa.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Dữ liệu trống", Toast.LENGTH_SHORT).show();
        }else {
            String sex;
            if (ragSex.getCheckedRadioButtonId()==R.id.rab_male) sex = "Nam";
            else sex = "Nữ";
            NhanVien nhanVien = new NhanVien(edtMa.getText().toString(), edtName.getText().toString(), sex);
            if (dbManager.checkMaNV(nhanVien.getMaNV())){
                Toast.makeText(MainActivity.this, "Mã nhân viên đã tồn tại", Toast.LENGTH_SHORT).show();
            }else{
                dbManager.addNV(nhanVien);
                nhanViens.add(nhanVien);
                adapter.notifyDataSetChanged();
            }
        }
    }
    public void deleteNV(View view) {
        for (int i = 0; i < nhanViens.size(); i++){
            View view1 = lv.getChildAt(i);
            CheckBox checkBox = view1.findViewById(R.id.check_delete);
            if (checkBox.isChecked()){
                dbManager.deleteNV(nhanViens.get(i).getMaNV());
                nhanViens.remove(i);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
