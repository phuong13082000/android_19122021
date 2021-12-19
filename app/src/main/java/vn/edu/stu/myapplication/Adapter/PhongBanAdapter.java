package vn.edu.stu.myapplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

import vn.edu.stu.myapplication.Database.Database;
import vn.edu.stu.myapplication.Model.Loai;
import vn.edu.stu.myapplication.R;
import vn.edu.stu.myapplication.UpdatePhongBanActivity;

public class PhongBanAdapter extends BaseAdapter {
    final String DATABASE_NAME = "data.db";
    SQLiteDatabase databaseLoai;
    SQLiteDatabase databaseTraiCay;
    ArrayList<Loai> listLoai;
    Activity context;

    public PhongBanAdapter(Activity context, ArrayList<Loai> listLoai) {
        this.context = context;
        this.listLoai = listLoai;
    }

    @Override
    public int getCount() {
        return listLoai.size();
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
        View row = inflater.inflate(R.layout.dong_phongban, null);

        TextView txtTenLoai = row.findViewById(R.id.textviewTenLoai);
        TextView txtLuong = row.findViewById(R.id.txtLuongPB);
        TextView txtDoanhthu = row.findViewById(R.id.txtDoanhthuPB);
        TextView txtTrocap = row.findViewById(R.id.txtTroCapPB);
        TextView txtSoluong = row.findViewById(R.id.txtSoLuongPB);

        Loai loai = listLoai.get(position);

        txtTenLoai.setText(loai.getLoai());
        txtLuong.setText(loai.getLuong() + "");
        txtDoanhthu.setText(loai.getDoanhThu() + "");
        txtTrocap.setText(loai.getTroCap() + "");
        txtSoluong.setText(loai.getSoLuong() + "");

        row.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdatePhongBanActivity.class);
            intent.putExtra("ID", loai.getID());
            context.startActivity(intent);
        });

        row.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setTitle(R.string.del1);
            builder.setMessage(R.string.del2);
            builder.setPositiveButton(R.string.Yes, (dialog, which) -> del_Option(loai));
            builder.setNegativeButton(R.string.No, (dialog, which) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return false;
        });
        return row;
    }

    private void del_Option(Loai l) {
        databaseTraiCay = Database.initDatabase(context, DATABASE_NAME);
        databaseLoai = Database.initDatabase(context, DATABASE_NAME);

        Cursor cursorTraiCay = databaseLoai.rawQuery("SELECT Loai FROM Phu WHERE Loai = ?", new String[]{l.getLoai()});

        String loaitc = null;
        while (cursorTraiCay.moveToNext()) {
            loaitc = cursorTraiCay.getString(0);
        }
        if (loaitc == null || loaitc.equals("")) {
            databaseLoai.delete("Loai", "Loai = ?", new String[]{(l.getLoai())});
            listLoai.clear();

            Cursor cursorLoai = databaseLoai.rawQuery("SELECT * FROM Loai", null);
            while (cursorLoai.moveToNext()) {
                int ID = cursorLoai.getInt(0);
                String Loai = cursorLoai.getString(1);
                listLoai.add(new Loai(ID, Loai));
            }
            notifyDataSetChanged();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(android.R.drawable.ic_delete);
            builder.setTitle(R.string.databinding);
            builder.setMessage(R.string.cantdel);
            builder.setPositiveButton(R.string.Yes, (dialog, which) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}
