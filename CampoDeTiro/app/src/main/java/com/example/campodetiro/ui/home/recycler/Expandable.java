package com.example.campodetiro.ui.home.recycler;

import java.util.List;

public interface Expandable<T> {
    boolean isExpandable();
    public abstract List<T> getListChildItems();
    public abstract boolean isExpanded();
    public abstract void setExpanded(boolean expanded);
}
