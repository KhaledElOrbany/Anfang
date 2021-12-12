package Utilities;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogsUtil extends AppCompatDialogFragment {
    private final String title;
    private final String message;
    private final String btnText;

    public DialogsUtil(String title, String message, String btnText) {
        this.title = title;
        this.message = message;
        this.btnText = btnText;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
        alert.setTitle(this.title)
                .setMessage(this.message)
                .setPositiveButton(this.btnText, (dialog, which) -> {
                });
        return alert.create();
    }
}
