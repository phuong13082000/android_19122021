package vn.edu.stu.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.stu.myapplication.Adapter.NhanVienAdapter;
import vn.edu.stu.myapplication.Database.Database;
import vn.edu.stu.myapplication.Model.NhanVien;

public class NhanVienActivity extends AppCompatActivity {
    final String DATABASE_NAME = "data.db";
    SQLiteDatabase database;

    ListView lvTraiCay;
    ArrayList<NhanVien> list;
    NhanVienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
        addControls();
        readData();
    }

    private void addControls() {
        lvTraiCay = findViewById(R.id.lvTraiCay);
        list = new ArrayList<>();
        adapter = new NhanVienAdapter(this, list);
        lvTraiCay.setAdapter(adapter);
    }

    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM Phu", null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Integer ID = cursor.getInt(0);
            String Ten = cursor.getString(1);
            Integer SoDienThoai = cursor.getInt(2);
            byte[] Anh = cursor.getBlob(3);
            String Loai = cursor.getString(4);
            Integer Luong = cursor.getInt(5);
            Integer LuongCoBan = cursor.getInt(6);

            list.add(new NhanVien(ID, Ten, SoDienThoai, Anh, Loai, Luong, LuongCoBan));
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nhanvien, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.themnv:
                Intent manhinh1 = new Intent(
                        NhanVienActivity.this,
                        AddNhanVienActivity.class);
                startActivity(manhinh1);
                break;
            case R.id.trangchu:
                Intent manhinh2 = new Intent(
                        NhanVienActivity.this,
                        MainActivity.class);
                startActivity(manhinh2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}