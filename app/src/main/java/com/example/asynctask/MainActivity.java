package com.example.asynctask;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    EditText txtURL;
    Button btnDownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtURL = (EditText) findViewById(R.id.edit_text);
        img =(ImageView) findViewById(R.id.img_view);
        btnDownload = (Button) findViewById(R.id.btn_download);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DownloadImage image = new DownloadImage();
                image.execute(txtURL.getText().toString());
            }
        });

    }
    private  Bitmap download(String urlstring){
        Bitmap bitmap=null;
        URL urlobj=null;
        HttpURLConnection httpcon = null;
        InputStream is=null;
        try {
            urlobj=new URL(urlstring);
            httpcon=(HttpURLConnection) urlobj.openConnection();
            is=httpcon.getInputStream();
            bitmap= BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            {
                is=null;
                httpcon.disconnect();
            }
        }
        return bitmap;

    }
    class DownloadImage extends AsyncTask<String, Void ,Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap rs = null;
            rs = download(strings[0]);
            return rs;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);

        }
    }
}