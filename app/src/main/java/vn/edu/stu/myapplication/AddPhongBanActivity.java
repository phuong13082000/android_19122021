package vn.edu.stu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.stu.myapplication.Database.Database;

public class AddPhongBanActivity extends AppCompatActivity {
    final String DATABASE_NAME = "data.db";
    Button btnThem, btnHuy;
    EditText txtPhongBan, txtLuong, txtDoanhThu, txtTroCap, txtSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phongban);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnThem = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);

        txtPhongBan = findViewById(R.id.txtPhongBan);
        txtLuong = findViewById(R.id.txtLuong);
        txtDoanhThu = findViewById(R.id.txtDoanhThu);
        txtTroCap = findViewById(R.id.txtTroCap);
        txtSoLuong = findViewById(R.id.txtSoLuong);
    }

    private void addEvents() {
        btnThem.setOnClickListener(v -> insert());

        btnHuy.setOnClickListener(v -> cancel());
    }

    private void cancel() {
        Intent intent = new Intent(this, PhongBanActivity.class);
        startActivity(intent);
    }

    private void insert() {
        String loai = txtPhongBan.getText().toString();
        int luong = Integer.parseInt(txtLuong.getText().toString());
        int doanhthu = Integer.parseInt(txtLuong.getText().toString());
        int trocap = Integer.parseInt(txtLuong.getText().toString());
        int soluong = Integer.parseInt(txtLuong.getText().toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put("Loai", loai);
        contentValues.put("Luong", luong);
        contentValues.put("DoanhThu", doanhthu);
        contentValues.put("TroCap", trocap);
        contentValues.put("SoLuong", soluong);
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        database.insert("Loai", null, contentValues);
        Toast.makeText(getApplicationContext(), R.string.successfulcreation, Toast.LENGTH_LONG).show();
    }

}