package com.example.campodetiro.ui.home.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campodetiro.R;

public class SimpleViewHolder extends RecyclerView.ViewHolder{
    public TextView simpleItemText;
    public TextView simpleItemValue;
    public SimpleViewHolder(@NonNull View itemView) {
        super(itemView);
        simpleItemText = itemView.findViewById(R.id.simple_item_text);
        simpleItemValue = itemView.findViewById(R.id.simple_item_value);
    }
}


