package vn.edu.stu.myapplication;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import vn.edu.stu.myapplication.Adapter.MainAdapter;
import vn.edu.stu.myapplication.Database.Database;
import vn.edu.stu.myapplication.Model.Loai;
import vn.edu.stu.myapplication.Model.NhanVien;


public class MainActivity extends AppCompatActivity {
    final String DATABASE_NAME = "data.db";
    SQLiteDatabase database;
    ListView lv;
    Spinner spinner;
    ArrayList<Loai> loais;
    ArrayList<NhanVien> list;
    MainAdapter adapter;
    String loai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        spinner();
        readData();
    }

    private void addControls() {
        spinner = findViewById(R.id.spinner);
        loais = new ArrayList<>();
        lv = findViewById(R.id.lv);
        list = new ArrayList<>();
        adapter = new MainAdapter(this, list);
        lv.setAdapter(adapter);
    }

    private void addEvents() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loai = getSelectItem(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void spinner() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT ID, Loai FROM Loai", null);
        loais.clear();
        while (cursor.moveToNext()) {
            Integer ID = cursor.getInt(0);
            String loai = cursor.getString(1);
            //Integer luong = cursor.getInt(2);
            //Integer doanhthu = cursor.getInt(3);
            //Integer trocap = cursor.getInt(4);
            //Integer soluong = cursor.getInt(5);

            Loai type = new Loai(ID, loai);
            loais.add(type);
        }
        ArrayAdapter<Loai> adapterLoai = new ArrayAdapter<Loai>(this, android.R.layout.simple_spinner_dropdown_item, loais);
        adapterLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterLoai);

        cursor.close();
        database.close();
    }

    public String getSelectItem(View v) {
        Loai loai = (Loai) spinner.getSelectedItem();
        return loai.getLoai();
    }

    private void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT ID, Ten, SoDienThoai, Anh, Loai FROM Phu", null);
        list.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Integer ID = cursor.getInt(0);
            String Ten = cursor.getString(1);
            Integer SoDienThoai = cursor.getInt(2);
            byte[] Anh = cursor.getBlob(3);
            String Loai = cursor.getString(4);

            list.add(new NhanVien(ID, Ten, SoDienThoai, Anh, Loai));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dschinh:
                Intent manhinh1 = new Intent(
                        MainActivity.this,
                        NhanVienActivity.class);
                startActivity(manhinh1);
                break;
            case R.id.dsloai:
                Intent manhinh2 = new Intent(
                        MainActivity.this,
                        PhongBanActivity.class);
                startActivity(manhinh2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}