package com.example.campodetiro.ui.home.recycler;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campodetiro.R;

public class ExpandableViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout expandableContent;
    public RecyclerView recyclerView;
    public TextView itemTitle;
    public TextView itemValue;

    public ExpandableViewHolder(@NonNull View itemView) {
        super(itemView);
        // Accedemos a las vistas desde el layout del ítem expandible
        itemTitle = itemView.findViewById(R.id.item_title);
        itemValue = itemView.findViewById(R.id.item_value);
        expandableContent = itemView.findViewById(R.id.expandable_content);
        recyclerView = itemView.findViewById(R.id.child_recycler_view);

    }

    public void bind(Item item) {
        itemTitle.setText(item.getTag());  // Configurar el título del ítem
    }
}
