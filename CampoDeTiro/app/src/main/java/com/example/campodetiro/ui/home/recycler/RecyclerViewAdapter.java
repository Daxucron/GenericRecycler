package com.example.campodetiro.ui.home.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campodetiro.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int VIEW_TYPE_SIMPLE = 0;
    private static final int VIEW_TYPE_EXPANDABLE = 1;
    private List<Item> items;
    private LayoutInflater inflater;

    public void setItems(List<Item> items){
        this.items = items;
    }

    public RecyclerViewAdapter(Context context, List<Item> items) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

    // Determina el tipo de vista (simple o expandible) según el ítem en la posición
    @Override
    public int getItemViewType(int position) {
        if (items.get(position).isExpandable()) {
            return VIEW_TYPE_EXPANDABLE;
        } else {
            return VIEW_TYPE_SIMPLE;
        }
    }

    // ########################################## onCreateViewHolder #################################################
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SIMPLE) {
            View view = inflater.inflate(R.layout.item_simple, parent, false);
            return new SimpleViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_expandable, parent, false);
            return new ExpandableViewHolder(view);
        }
    }

    // ########################################## onBindViewHolder #################################################
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SimpleViewHolder) {
            SimpleViewHolder simpleViewHolder = (SimpleViewHolder) holder;
            onBindSimpleViewHolder(simpleViewHolder, position);

        } else if (holder instanceof ExpandableViewHolder) {
            ExpandableViewHolder expandableHolder = (ExpandableViewHolder) holder;
            onBindExpandableViewHolder(expandableHolder, position);
        }
    }

    private void onBindSimpleViewHolder(SimpleViewHolder simpleViewHolder, int position) {
        SimpleItem item = (SimpleItem) items.get(position);
        simpleViewHolder.simpleItemText.setText(item.getTag());
        simpleViewHolder.simpleItemValue.setText(item.getValue());
        if(item instanceof ModifiableSimpleItem){
            simpleViewHolder.itemView.setOnClickListener(v -> {
                // Toast.makeText(v.getContext(), "Este es un mensaje Toast", Toast.LENGTH_SHORT).show();
                CustomDialog customDialog = new CustomDialog(v.getContext(), this, position);
                customDialog.show();
            });
        }
    }

    private void onBindExpandableViewHolder(ExpandableViewHolder holder, int position){
        ExpandableItem item = (ExpandableItem) items.get(position);
        holder.itemTitle.setText(item.getTag());
        holder.itemValue.setText(item.getValue());
        setupChildRecyclerView(holder.recyclerView, item.getListChildItems());

        if (item.isExpanded()) {
            holder.expandableContent.setVisibility(View.VISIBLE);
        } else {
            holder.expandableContent.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(v -> {
            item.setExpanded(!item.isExpanded());
            notifyItemChanged(position);
        });
    }

    public void updateItemValue(int position, String newValue) {

        if(items.get(position) instanceof ModifiableSimpleItem){
            ModifiableSimpleItem item = (ModifiableSimpleItem)items.get(position);
            item.newValue(newValue);
            notifyItemChanged(position);
        }
    }

    // ###############################################################################################################

    private void setupChildRecyclerView(RecyclerView recyclerView, List<Item> childItems) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Reutilizamos el mismo adaptador para los ítems hijos
        RecyclerViewAdapter childAdapter = new RecyclerViewAdapter(recyclerView.getContext(), childItems);
        recyclerView.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}