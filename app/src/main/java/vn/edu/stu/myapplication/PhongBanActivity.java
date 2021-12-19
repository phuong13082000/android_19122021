package vn.edu.stu.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.stu.myapplication.Adapter.PhongBanAdapter;
import vn.edu.stu.myapplication.Database.Database;
import vn.edu.stu.myapplication.Model.Loai;

public class PhongBanActivity extends AppCompatActivity {

    final String DATABASE_NAME = "data.db";
    SQLiteDatabase database;
    ListView lvLoai;
    ArrayList<Loai> listLoai;
    PhongBanAdapter adapterLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phongban);
        addControls();
        addEvents();
        readData();
    }

    private void addControls() {
        lvLoai = findViewById(R.id.lvLoai);
        listLoai = new ArrayList<>();
        adapterLoai = new PhongBanAdapter(this, listLoai);
        lvLoai.setAdapter(adapterLoai);
    }

    private void addEvents() {
    }

    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM Loai", null);
        listLoai.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int ID = cursor.getInt(0);
            String Loai = cursor.getString(1);
            int Luong = cursor.getInt(2);
            int DoanhThu = cursor.getInt(3);
            int TroCap = cursor.getInt(4);
            int SoLuong = cursor.getInt(5);

            listLoai.add(new Loai(ID, Loai, Luong, DoanhThu, TroCap, SoLuong));
        }
        adapterLoai.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_phongban, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.themnv:
                Intent manhinh1 = new Intent(
                        PhongBanActivity.this,
                        AddPhongBanActivity.class);
                startActivity(manhinh1);
                break;
            case R.id.trangchu:
                Intent manhinh2 = new Intent(
                        PhongBanActivity.this,
                        MainActivity.class);
                startActivity(manhinh2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}