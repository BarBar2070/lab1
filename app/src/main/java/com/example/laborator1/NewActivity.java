package com.example.laborator1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent();
        Bitmap photo = (Bitmap) intent.getParcelableExtra("capturedPhoto");
        imageView.setImageBitmap(photo);

      /*  Bundle extras = getIntent().getExtras();
        if (extras != null) {
            byte[] byteArray = extras.getByteArray("picture");

            if (byteArray.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                ImageView image = findViewById(R.id.imageView);
                image.setImageBitmap(bitmap);
            }
        }*/
    }
}
