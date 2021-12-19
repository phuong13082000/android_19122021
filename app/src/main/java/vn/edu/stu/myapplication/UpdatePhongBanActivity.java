package vn.edu.stu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.stu.myapplication.Database.Database;

public class UpdatePhongBanActivity extends AppCompatActivity {
    final String DATABASE_NAME = "data.db";
    int id = -1;
    Cursor cursor;
    SQLiteDatabase database;
    Button btnThem, btnHuy;
    EditText txtPhongBan, txtLuong, txtDoanhThu, txtTroCap, txtSoLuong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phongban);
        addControls();
        addEvents();
        initUI();
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
        btnThem.setOnClickListener(v -> updateLoai());

        btnHuy.setOnClickListener(v -> cancel());
    }

    private void initUI() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -1);
        database = Database.initDatabase(this, DATABASE_NAME);
        cursor = database.rawQuery("SELECT * FROM Loai WHERE ID = ?", new String[]{id + ""});
        cursor.moveToFirst();
        String Loai = cursor.getString(1);
        int Luong = cursor.getInt(2);
        int DoanhThu = cursor.getInt(3);
        int TroCap = cursor.getInt(4);
        int SoLuong = cursor.getInt(5);

        txtPhongBan.setText(Loai);
        txtLuong.setText(Luong + "");
        txtDoanhThu.setText(DoanhThu + "");
        txtTroCap.setText(TroCap + "");
        txtSoLuong.setText(SoLuong + "");
    }

    private void cancel() {
        Intent intent = new Intent(this, PhongBanActivity.class);
        startActivity(intent);
    }

    private void updateLoai() {
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
        database.update("Loai", contentValues, "id = ?", new String[]{id + ""});
        database.update("Phu", contentValues, "Loai = ?", new String[]{loai});
        Toast.makeText(getApplicationContext(), R.string.fix, Toast.LENGTH_LONG).show();
    }

}