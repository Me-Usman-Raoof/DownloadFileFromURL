package com.paulfrank.downloadpdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ContentInfoCompat;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paulfrank.makeitdownload.FileDownloaderService;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private Button btnDownload;
    private TextView progressCount;

    //    String pdfurl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
    String pdfurl = "https://sc-staging.algoras.com/images/logo.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownload = findViewById(R.id.btnDownload);
        progressCount = findViewById(R.id.progressCount);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("FOO", "Button Clicked");

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Choose Download Method");
                builder.setNegativeButton("Downlaod Manager", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        downloadPDF(pdfurl);
                    }
                });
                builder.setPositiveButton("Using Browser", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pdfurl)));
                    }
                });
                builder.show();
            }
        });

    }

    private void downloadPDF(String pdfURL) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pdfURL));
        request.setTitle("Downloading the Report");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, getFileNameWithExtensionFromURL(pdfURL));
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);
    }

    private String getFileNameWithExtensionFromURL(String fileURL) {
        Uri uri = Uri.parse(fileURL);
        return uri.getLastPathSegment();
    }
}