package Globals;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class Globals {
    // localhost: 127.0.0.1
    public static String BaseUrl = "http://192.168.1.50:5000/api/nodes/";
    @NonNull
    public static ProgressDialog ShowLoadingPanel(Context context, String title, String msg) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle(title);
        progress.setMessage(msg);
        progress.setCancelable(false);
        progress.show();
        return progress;
    }
}
