package com.example.zesc.imagen_v1;

import android.content.Intent;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.InputStream;

import static android.view.View.*;


public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int REQUEST_CODE = 1;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate!");
        setContentView(R.layout.activity_main);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do some magic here to open gallery?
                Intent intent =  new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select..."), REQUEST_CODE);
            }
        };
        findViewById(R.id.button1).setOnClickListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            Log.d(TAG, uri.toString());
            Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_LONG).show();

            try {
                InputStream stream = getContentResolver().openInputStream(uri);
                mBitmap = BitmapFactory.decodeStream(stream);
                ImageView v = (ImageView) findViewById(R.id.imageView1);
                v.setImageBitmap(mBitmap);
            } catch (Exception e) {
                Log.d(TAG, "Decoding Bitmap", e);
            }

        }
    }
}
