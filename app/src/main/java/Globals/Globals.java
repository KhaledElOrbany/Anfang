package Globals;

import android.app.ProgressDialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class Globals {
    @NonNull
    public static ProgressDialog ShowLoadingPanel(Context context) {
        ProgressDialog progress = new ProgressDialog(context);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false);
        progress.show();
        return progress;
    }
}
