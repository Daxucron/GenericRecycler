package com.example.campodetiro.ui.home.recycler;

import java.util.List;

public class ExpandableItem extends Item implements Expandable<Item>{

    private List<Item> childItems;
    private boolean expanded;

    public ExpandableItem(String title, List<Item> childItems) {
        setTag(title);
        this.childItems = childItems;
    }
    public ExpandableItem(){}

    public void setChildItems(List<Item> childItems)
    {
        this.childItems = childItems;
    }

    public void addChildItem(Item childItem)
    {
        this.childItems.add(childItem);
    }

    @Override
    public List<Item> getListChildItems() {
        return childItems;
    }

    @Override
    public boolean isExpanded()
    {
        return expanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }


}
