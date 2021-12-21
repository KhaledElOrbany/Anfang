package Globals;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class Globals {
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
