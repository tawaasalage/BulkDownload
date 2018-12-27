package com.apex.test.bulkdownload;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.apex.test.bulkdownload.Model.FilesModel;
import com.apex.test.bulkdownload.Util.BulkDownloadHelper;
import com.apex.test.bulkdownload.Util.UploadStatusCallback;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.WRITE_EXTERNAL_STORAGE  },1000 );
            }

            if ( ContextCompat.checkSelfPermission( this, Manifest.permission.INTERNET ) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.INTERNET  },1000 );
            }

        }

        ArrayList<FilesModel> files=new ArrayList<>();

        for (int i=1;i<200;i++)
        {
            FilesModel filesModel=new FilesModel();
            filesModel.setSavename(i+".jpeg");

            if(i>50 && i<100)
            {
                filesModel.setSavePath(AllConstants.IMAGEDIRECTORY+"F50");
            }
            else if(i>100 && i<150)
            {
                filesModel.setSavePath(AllConstants.IMAGEDIRECTORY+"F150");
            }
            else
            {
                filesModel.setSavePath(AllConstants.IMAGEDIRECTORY+"F200");
            }

            filesModel.setUrl("http://192.168.1.10/ImageTest/test.jpeg");
            files.add(filesModel);
        }

        BulkDownloadHelper dImg=new BulkDownloadHelper(MainActivity.this);
        dImg.DownoloadFiles(files, new UploadStatusCallback() {
            @Override
            public void onCompleted() {

                Log.d("DownloadTest","End");
            }

            @Override
            public void onError() {

            }
        });*/

    }


}
