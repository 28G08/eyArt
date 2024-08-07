package com.galuhsukma.bismillah;

import static com.galuhsukma.bismillah.Database.DB_TABLE;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    Context context;
    int allpostData;
    ArrayList<Model>modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    //generate constructor

    public DataAdapter(Context context, int allpostData, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.allpostData = allpostData;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.allpost_data, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Model model = modelArrayList.get(position);
        int id = model.getId();
        int favorite = model.getFavorite();
        byte[] image = model.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imageView.setImageBitmap(bitmap);
        holder.title.setText(model.getTitle());
        holder.about.setText(model.getAbout());
        final int itemPosition = position;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            Database myDB = new Database(context);
            @Override
            public void onClick(View view) {
                sqLiteDatabase = myDB.getReadableDatabase();
                long delete = sqLiteDatabase.delete(DB_TABLE, "_id = "+model.getId(), null);
                if (delete != -1){
                    Toast.makeText(context, "Delete Data Successfully", Toast.LENGTH_SHORT).show();
                    modelArrayList.remove(itemPosition);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title, about;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.gambar);
            title = (TextView)itemView.findViewById(R.id.judul);
            about = (TextView) itemView.findViewById(R.id.konten);
            delete = (Button) itemView.findViewById(R.id.delete_button_id);
        }
    }
}
