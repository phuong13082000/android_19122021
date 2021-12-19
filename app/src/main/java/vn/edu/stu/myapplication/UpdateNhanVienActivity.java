package vn.edu.stu.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import vn.edu.stu.myapplication.Database.Database;
import vn.edu.stu.myapplication.Model.Loai;

public class UpdateNhanVienActivity extends AppCompatActivity {
    final String DATABASE_NAME = "data.db";
    final int REQUEST_TAKE_PHOTO = 123;
    final int REQUEST_CHOOSE_PHOTO = 321;
    SQLiteDatabase database;
    ArrayList<Loai> loais;
    Button btnChonHinh, btnChupHinh, btnLuu, btnHuy;
    EditText txtTen, txtSDT, txtLuong;
    ImageView imgAVT;
    Spinner spinner;
    Cursor cursor;
    String loai;
    int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nhanvien);
        addControls();
        addEvents();
        initUI();
    }

    private void addControls() {
        btnChonHinh = findViewById(R.id.btnChonHinh);
        btnChupHinh = findViewById(R.id.btnChupHinh);
        btnLuu = findViewById(R.id.btnThem);
        btnHuy = findViewById(R.id.btnHuy);
        txtTen = findViewById(R.id.txtTen);
        txtSDT = findViewById(R.id.txtSDT);
        spinner = findViewById(R.id.spinner);
        txtLuong = findViewById(R.id.txtSoLuong);
        imgAVT = findViewById(R.id.imgAVT);
        loais = new ArrayList<>();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(v -> update());

        btnHuy.setOnClickListener(v -> cancel());

        btnChonHinh.setOnClickListener(v -> choosePhoto());

        btnChupHinh.setOnClickListener(v -> takePicture());

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

    private void initUI() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", -1);
        database = Database.initDatabase(this, DATABASE_NAME);
        cursor = database.rawQuery("SELECT * FROM Phu WHERE ID = ?", new String[]{id + ""});
        cursor.moveToFirst();
        String Ten = cursor.getString(1);
        Integer SoDT = cursor.getInt(2);
        byte[] Anh = cursor.getBlob(3);
        String Loaisp = cursor.getString(4);
        Integer Luong = cursor.getInt(5);
        Bitmap bitmap = BitmapFactory.decodeByteArray(Anh, 0, Anh.length);
        imgAVT.setImageBitmap(bitmap);
        txtTen.setText(Ten);
        txtSDT.setText(SoDT+ "");
        txtLuong.setText(Luong + "");

        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        loais.clear();
        Cursor cursor1 = database.rawQuery("SELECT * FROM Loai Where Loai = ?", new String[]{Loaisp});
        Cursor cursor2 = database.rawQuery("SELECT * FROM Loai Where Loai <> ?", new String[]{Loaisp});
        while (cursor1.moveToNext()) {
            String Loai = cursor1.getString(1);
            Loai type = new Loai(Loai);
            loais.add(type);
        }
        while (cursor2.moveToNext()) {
            String Loai = cursor2.getString(1);
            Loai type = new Loai(Loai);
            loais.add(type);
        }
        ArrayAdapter<Loai> adapterLoai = new ArrayAdapter<Loai>(this, android.R.layout.simple_spinner_dropdown_item, loais);
        adapterLoai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterLoai);
        cursor1.close();
        cursor2.close();
        database.close();
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CHOOSE_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CHOOSE_PHOTO) {
                Uri imgUri = data.getData();
                try {
                    InputStream is = getContentResolver().openInputStream(imgUri);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    imgAVT.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_TAKE_PHOTO) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgAVT.setImageBitmap(bitmap);
            }
        }
    }

    private void update() {
        String ten = txtTen.getText().toString();
        int sdt = Integer.parseInt(txtSDT.getText().toString());
        int luong = Integer.parseInt(txtLuong.getText().toString());
        byte[] anh = getByteArrayFromImageView(imgAVT);
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten", ten);
        contentValues.put("SoDienThoai", sdt);
        contentValues.put("Anh", anh);
        contentValues.put("Loai", String.valueOf(loai));
        contentValues.put("Luong", luong);
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        database.update("Phu", contentValues, "id = ?", new String[]{id + ""});
        Toast.makeText(getApplicationContext(), R.string.fix, Toast.LENGTH_LONG).show();
    }

    private void cancel() {
        Intent intent = new Intent(this, NhanVienActivity.class);
        startActivity(intent);
    }

    private byte[] getByteArrayFromImageView(ImageView imgv) {
        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public String getSelectItem(View v) {
        Loai loai = (Loai) spinner.getSelectedItem();
        return loai.getLoai();
    }

}