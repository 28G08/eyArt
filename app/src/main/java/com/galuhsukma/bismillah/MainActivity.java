package com.galuhsukma.bismillah;

import static com.galuhsukma.bismillah.Database.DB_TABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database myDB;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new Database(this);
        findId();

        displayData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void displayData() {
        sqLiteDatabase = myDB.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+DB_TABLE+"", null);
        ArrayList<Model> models = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            byte[] image = cursor.getBlob(1);
            String title = cursor.getString(2);
            String about = cursor.getString(3);
            int favorite = cursor.getInt(4);
            models.add(new Model(id, image, title, about, favorite));
        }
        cursor.close();
        dataAdapter = new DataAdapter(this, R.layout.allpost_data, models, sqLiteDatabase);
        recyclerView.setAdapter(dataAdapter);
    }

    private void findId() {
        recyclerView = findViewById(R.id.rv_data);
    }

    public void addpostpage(View view) {
        Intent intent = new Intent(this, AddPostActivity.class);
        startActivity(intent);
    }

    public void aboutpage(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public Bitmap bytetoArrayToBitmap(byte[] byteArray) {
        if (byteArray != null) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }
}