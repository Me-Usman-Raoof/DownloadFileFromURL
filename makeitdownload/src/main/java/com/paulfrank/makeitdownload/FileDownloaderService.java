package com.paulfrank.makeitdownload;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.ProgressBar;

public class FileDownloaderService {
    private Context context = null;
    private String fileURL = null;
    private Integer notificationVisibility = null;

    private ProgressBar progressBar = null;

    public FileDownloaderService(Context context,
                                 String fileURL,
                                 Integer notificationVisibility) {
        this.context = context;
        this.fileURL = fileURL;
        this.notificationVisibility = notificationVisibility;
    }

    public void startDownloading() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileURL));
        request.setTitle("Downloading the Report");
        request.setNotificationVisibility(notificationVisibility);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, getFileNameWithExtensionFromURL(fileURL));
        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    private String getFileNameWithExtensionFromURL(String fileURL) {
        Uri uri = Uri.parse(fileURL);
        return uri.getLastPathSegment();
    }

    // Add this method to set the ProgressBar
    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    // Update the ProgressBar with the download progress
    private void updateProgressBar(int progress) {
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
    }
}
