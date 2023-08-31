package com.paulfrank.makeitdownload;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class FileDownloaderService {
    private Context context = null;
    private String fileURL = null;
    private String notificationTitle = null;
    private Integer notificationVisibility = null;

    public FileDownloaderService(Context context,
                                 String fileURL,
                                 String notificationTitle,
                                 Integer notificationVisibility) {
        this.context = context;
        this.fileURL = fileURL;
        this.notificationTitle = notificationTitle;
        this.notificationVisibility = notificationVisibility;
    }

    public void startDownloading() {

        if (isValidate()) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileURL));
            request.setTitle("Downloading the Report");
            request.setNotificationVisibility(notificationVisibility);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, getFileNameWithExtensionFromURL(fileURL));
            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
        } else {
            Log.d("FOO", "Something Went Wrong While Downloading The File");
        }
    }

    protected boolean isValidate() {
        int value = 0;
        if (context == null) {
            value = 1;
            Log.d("FOO", "Context Is Null While Downloading File");
        } else if (fileURL == null || TextUtils.isEmpty(fileURL)) {
            value = 1;
            Log.d("FOO", "File Url Is Null While Downloading File");
        } else if (notificationTitle == null || TextUtils.isEmpty(notificationTitle)) {
            notificationTitle = "Downloading File";
        } else if (notificationVisibility == null) {
            notificationVisibility = 1;
        } else {
            value = 0;
        }
        return value == 0;
    }

    private String getFileNameWithExtensionFromURL(String fileURL) {
        Uri uri = Uri.parse(fileURL);
        return uri.getLastPathSegment();
    }
}
