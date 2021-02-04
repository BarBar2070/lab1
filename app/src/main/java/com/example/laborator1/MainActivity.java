package com.example.laborator1;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import static android.telephony.AvailableNetworkInfo.PRIORITY_HIGH;
import static com.example.laborator1.R.id.idLayout;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private Bitmap capturedImage;
    private static final int REQUEST_CODE = 100;
    private NotificationManager notificationManager;
    private static final int NOTIFICATION_ID = 000;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    //private static final int REQUEST_ID = 100;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pushNotification(View view) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                createNotificationChannel();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder.setContentTitle("Notificare !!!!");
                builder.setContentText("Aceasta este notificarea creata dupa apasarea butonului");
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);
                notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
            }
        },1000);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.BASE)
        {
            CharSequence name = "Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void searchText(View view){
        EditText input = findViewById(R.id.inputtxt);
        String text = input.getText().toString();
        if (text.length()>0)
        {
            String finalUrl = "https://www.google.com/search?q=" + text;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(finalUrl));
            startActivity(intent);

        }
    }

    public void cameraOpen(View view) {
       // Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        RadioButton frontRadio = findViewById(R.id.frontRadio);
        RadioButton backRadio = findViewById(R.id.backRadio);


        if (frontRadio.isChecked()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
            startActivityForResult(intent, 1);
        } else if (backRadio.isChecked()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, 1);
        }
        /*if (frontRadio.isChecked())
        {
            intent.putExtra("android.intent.extras.LENS_FACING_FRONT", true);
            intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true);
            intent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_FRONT);
        } else if (backRadio.isChecked()) {
            intent.putExtra("android.intent.extras.LENS_FACING_FRONT", false);
            intent.putExtra("android.intent.extra.USE_FRONT_CAMERA", false);
            intent.putExtra("android.intent.extras.CAMERA_FACING", Camera.CameraInfo.CAMERA_FACING_BACK);
        } else {
            return;
        }
        startActivityForResult(intent, REQUEST_CODE);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        // BitMap is data structure of image file
        // which store the image in memory
        Bitmap photo = (Bitmap) data.getExtras().get("data");

        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("capturedPhoto", photo);
        startActivity(intent);
    }

       /* super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            capturedImage = (Bitmap) extras.get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            capturedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            Intent intent = new Intent(MainActivity.this, NewActivity.class);
            intent.putExtra("picture", byteArray);
            startActivity(intent);
        }*/


    public void FinishApp(View v)
    {
        finish();
        System.exit(0);
    }

    public void openSnackbar(View view) {
        Snackbar snackbar = Snackbar.make(findViewById(idLayout), "Testing snackbar", Snackbar.LENGTH_LONG)
                .setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Something
                    }
                });
        snackbar.show();
    }
    public void close(Veiw v)
    {
        finish();
    }




}
