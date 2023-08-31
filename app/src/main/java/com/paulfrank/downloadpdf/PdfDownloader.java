package com.paulfrank.downloadpdf;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfDownloader extends AsyncTask<String, Integer, String> {

    private Context context;
    private ProgressDialog progressDialog;

    public PdfDownloader(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Downloading PDF...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String pdfUrl = params[0];
        int count;
        try {
            URL url = new URL(pdfUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            int lengthOfFile = connection.getContentLength();

            File externalStorageDir = Environment.getExternalStorageDirectory();
            File pdfFile = new File(externalStorageDir, "downloaded_file.pdf");
            FileOutputStream outputStream = new FileOutputStream(pdfFile);

            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            byte[] data = new byte[1024];

            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress((int) ((total * 100) / lengthOfFile));
                outputStream.write(data, 0, count);
            }

            outputStream.flush();
            outputStream.close();
            input.close();

        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
        }

        return "Downloaded at: " + Environment.getExternalStorageDirectory() + "/downloaded_file.pdf";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.dismiss();
        Log.d("Download Result", result);
    }
}
