package com.galuhsukma.bismillah;

import static com.galuhsukma.bismillah.Database.DB_TABLE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddPostActivity extends AppCompatActivity {

    Database database;
    SQLiteDatabase sqLiteDatabase;
    ImageView image;

    EditText title, about;
    int favorite;
    byte[] bytesArray;
    Button uploadButton, saveButton;

    DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        database = new Database(this);

        image = findViewById(R.id.upload_image_view);
        title = findViewById(R.id.title_edittext_id);
        about = findViewById(R.id.about_edittext_id);
        uploadButton = findViewById(R.id.upload_button_id);
        saveButton = findViewById(R.id.save_button_id);

        //pickGallery
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }

        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorite = 0;
                imageViewtoByte();
                database.insertData(bytesArray, title.getText().toString(), about.getText().toString(), favorite);
                image.setImageResource(0);
                title.setText("");
                about.setText("");
                Toast.makeText(AddPostActivity.this,"Data Saved Successfully", Toast.LENGTH_SHORT).show();
                dataAdapter.notifyDataSetChanged();
            }
        });

    }

    private void imageViewtoByte() {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        bytesArray = stream.toByteArray();
    }

    //pickGallery
    public void upload() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        uploadLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> uploadLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Uri uriImage = result.getData().getData();
                    image.setImageURI(uriImage);
                }
            });

    //tombolBackHome
    public void BackAddPost(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        finish();
    }


}