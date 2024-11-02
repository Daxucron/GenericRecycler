package com.example.campodetiro.ui.home.recycler;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.example.campodetiro.R;

public class CustomDialog extends Dialog {

    public CustomDialog(@NonNull Context context, RecyclerViewAdapter adapter, int position) {
        super(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);
        setContentView(dialogView);

        EditText editText = dialogView.findViewById(R.id.edit_text);
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnApply = dialogView.findViewById(R.id.btn_apply);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // Configuración del botón Apply
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editText.getText().toString();
                adapter.updateItemValue(position, inputText);
                Toast.makeText(context, "Texto ingresado: " + inputText, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }
}
