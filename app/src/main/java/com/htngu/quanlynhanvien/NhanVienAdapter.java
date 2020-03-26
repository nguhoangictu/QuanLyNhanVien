package com.htngu.quanlynhanvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.htngu.quanlynhanvien.database.DBManager;

import java.util.ArrayList;

public class NhanVienAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<NhanVien> list;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_nv, viewGroup, false);
        TextView textView;
        ImageView imgSex = view.findViewById(R.id.img_sex);
        textView = view.findViewById(R.id.textView);

        NhanVien nv = list.get(i);
        textView.setText(nv.getMaNV()+"-"+nv.getTenNV());

        if (nv.getGioiTinh().equals("Nam")) imgSex.setBackgroundResource(R.drawable.male);
        else imgSex.setBackgroundResource(R.drawable.female);
        return view;
    }
}
