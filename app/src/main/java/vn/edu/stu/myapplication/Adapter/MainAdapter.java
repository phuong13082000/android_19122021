package vn.edu.stu.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.stu.myapplication.Model.NhanVien;
import vn.edu.stu.myapplication.R;

public class MainAdapter extends BaseAdapter {
    final String DATABASE_NAME = "data.db";

    Activity context1;
    ArrayList<NhanVien> list2;

    public MainAdapter(Activity context1, ArrayList<NhanVien> list2) {
        this.context1 = context1;
        this.list2 = list2;
    }

    @Override
    public int getCount() {
        return list2.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.main_nhanvien, null);
        ImageView imgAVT = row.findViewById(R.id.imageviewHinh);
        TextView txtTen = row.findViewById(R.id.textviewTen);
        TextView txtLoai = row.findViewById(R.id.textviewLoai);
        NhanVien nhanVien = list2.get(position);
        txtTen.setText(nhanVien.getTen());
        txtLoai.setText(nhanVien.getLoai());
        Bitmap bmAVT = BitmapFactory.decodeByteArray(nhanVien.getAnh(), 0, nhanVien.getAnh().length);
        imgAVT.setImageBitmap(bmAVT);

        return row;
    }
}
