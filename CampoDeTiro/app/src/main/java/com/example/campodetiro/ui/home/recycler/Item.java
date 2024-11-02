package com.example.campodetiro.ui.home.recycler;

import androidx.annotation.NonNull;

public abstract class Item{
    private String tag;
    private String value;
    // Método que verifica si el ítem es expandible usando instanceof
    public boolean isExpandable() {
        return this instanceof Expandable;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue() {return value;}
    public void setValue(String value) {this.value = value;}

    @NonNull
    @Override
    public String toString() {

        return new StringBuilder("Item INFO: ").append(tag).toString();
    }
}
