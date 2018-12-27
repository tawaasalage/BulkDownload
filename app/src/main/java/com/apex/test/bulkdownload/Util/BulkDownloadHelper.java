package com.apex.test.bulkdownload.Util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.WindowManager;

import com.apex.test.bulkdownload.Model.FilesModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BulkDownloadHelper {

    UploadStatusCallback uploadStatusCallback;
    Activity context;
    ProgressDialog pDialog;

    public BulkDownloadHelper(Activity context)
    {
        this.context=context;
    }

    private class DownloadsOperation extends AsyncTask<ArrayList<FilesModel>, Void, Integer> {

        @Override
        protected Integer doInBackground(ArrayList<FilesModel>... arrayLists) {

            int i=1;
            int count=arrayLists[0].size();
            for (FilesModel model:arrayLists[0])
            {
                updateText(i,count);
                downloadBineryImages(model);
                Log.d("DownloadTest",i+" Downloaded");
                i++;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {

            cancelProgessDownload();
            uploadStatusCallback.onCompleted();
            super.onPostExecute(integer);
        }
    }

    public void downloadBineryImages(FilesModel model)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL url = new URL(model.getUrl());

            Log.d("DownloadStatus", model.getUrl());

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);

            //connect
            urlConnection.connect();

            int code=urlConnection.getResponseCode();

            Log.d("ServiceHandlerMalCode", code+"");
            //set the path where we want to save the file
            File SDCardRoot =new File(Environment.getExternalStorageDirectory()+ model.getSavePath());
            if(!SDCardRoot.exists())
            {
                SDCardRoot.mkdirs();
            }

            if(code==404)
            {
                return ;
            }

            //create a new file, to save the downloaded file
            File file = new File(SDCardRoot,model.getSavename());
            FileOutputStream fileOutput = new FileOutputStream(file);
            //Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();
            //create a buffer...
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();

        } catch (final MalformedURLException e) {
            Log.d("ServiceHandlerMal", e.toString());
            e.printStackTrace();

        } catch (final IOException e) {
            Log.d("ServiceHandler", e.toString());
            e.printStackTrace();

        }
        catch (final Exception e) {
            Log.d("ServiceHandler", e.toString());
        }

        return ;
    }


    public void DownoloadFiles(ArrayList<FilesModel> files, UploadStatusCallback uploadStatusCallback)
    {
        Log.d("DownloadTest","Start");

        showProgessDownload();
        this.uploadStatusCallback=uploadStatusCallback;
        new DownloadsOperation().execute(files);

    }

    public void DownoloadFilesByForce(ArrayList<FilesModel> files, UploadStatusCallback uploadStatusCallback)
    {
        Log.d("DownloadTest","Start");

        showProgessDownload();
        this.uploadStatusCallback=uploadStatusCallback;
        int i=1;
        int count=files.size();
        for (FilesModel model:files)
        {
            updateText(i,count);
            downloadBineryImages(model);
            Log.d("DownloadTest",i+" Downloaded");
            i++;
        }
        uploadStatusCallback.onCompleted();

    }

    public void showProgessDownload()
    {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void cancelProgessDownload()
    {
        if(pDialog!=null)
        {
            pDialog.dismiss();
        }

    }

    public void updateText(final int count,final int total)
    {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pDialog.setMessage("Please wait..."+count+"/"+total);
            }
        });

    }

}
