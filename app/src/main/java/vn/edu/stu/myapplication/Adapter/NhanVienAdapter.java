package vn.edu.stu.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.appcompat.app.AlertDialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.stu.myapplication.Database.Database;
import vn.edu.stu.myapplication.Model.NhanVien;
import vn.edu.stu.myapplication.R;
import vn.edu.stu.myapplication.UpdateNhanVienActivity;

public class NhanVienAdapter extends BaseAdapter {
    final String DATABASE_NAME = "data.db";

    Activity context;
    ArrayList<NhanVien> list;

    public NhanVienAdapter(Activity context, ArrayList<NhanVien> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.dong_nhanvien, null);
        ImageView imgAVT = row.findViewById(R.id.imageviewHinh);
        TextView txtTen = row.findViewById(R.id.textviewTen);
        TextView txtLoai = row.findViewById(R.id.textviewLoai);
        TextView txtSDT = row.findViewById(R.id.textviewSDT);
        TextView txtLuong = row.findViewById(R.id.textviewLuong);

        NhanVien nhanVien = list.get(position);

        txtTen.setText(nhanVien.getTen());
        txtLoai.setText(nhanVien.getLoai());
        txtSDT.setText(nhanVien.getSoDienThoai() + "");
        txtLuong.setText(nhanVien.getLuong() + "");
        Bitmap bmAVT = BitmapFactory.decodeByteArray(nhanVien.getAnh(), 0, nhanVien.getAnh().length);
        imgAVT.setImageBitmap(bmAVT);

        row.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setTitle(R.string.del1);
            builder.setMessage(R.string.del2);
            builder.setPositiveButton(R.string.Yes, (dialog, which) -> delete(nhanVien.getID()));
            builder.setNegativeButton(R.string.No, (dialog, which) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        });
        row.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateNhanVienActivity.class);
            intent.putExtra("ID", nhanVien.getID());
            context.startActivity(intent);
        });
        return row;
    }

    private void delete(int id) {
        SQLiteDatabase database = Database.initDatabase(context, DATABASE_NAME);
        database.delete("Phu", "ID = ?", new String[]{id + ""});
        list.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM Phu", null);
        while (cursor.moveToNext()) {
            Integer ID = cursor.getInt(0);
            String Ten = cursor.getString(1);
            Integer SoDienThoai = cursor.getInt(2);
            byte[] Anh = cursor.getBlob(3);
            String Loai = cursor.getString(4);
            Integer Luong = cursor.getInt(5);
            Integer LuongCoBan = cursor.getInt(6);

            list.add(new NhanVien(ID, Ten, SoDienThoai, Anh, Loai, Luong, LuongCoBan));
        }
        notifyDataSetChanged();
    }


}
