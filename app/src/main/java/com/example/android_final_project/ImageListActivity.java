package com.example.android_final_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.android_final_project.image_processing.ImageAdapter;
import com.example.android_final_project.image_processing.db.ImageEntity;
import com.example.android_final_project.image_processing.db.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        List<Bitmap> imageList = new ArrayList<>();
        ImageAdapter adapter = new ImageAdapter(imageList);
        recyclerView.setAdapter(adapter);
        populateRecyclerViewFromDatabase();
    }

    private void populateRecyclerViewFromDatabase() {
        new Thread(() -> {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "image-db").build();
            List<ImageEntity> images = db.imageDao().getAllImages();

            runOnUiThread(() -> {
                List<Bitmap> imageList = new ArrayList<>();
                for (ImageEntity imageEntity : images) {
                    Bitmap bitmap = BitmapFactory.decodeFile(imageEntity.path);
                    if (bitmap != null) {
                        imageList.add(bitmap);
                    }
                }
                ImageAdapter adapter = new ImageAdapter(imageList);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }

}
