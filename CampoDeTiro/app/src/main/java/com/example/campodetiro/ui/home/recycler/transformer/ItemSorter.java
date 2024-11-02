package com.example.campodetiro.ui.home.recycler.transformer;

import com.example.campodetiro.ui.home.recycler.ExpandableItem;
import com.example.campodetiro.ui.home.recycler.Item;
import com.example.campodetiro.ui.home.recycler.SimpleItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemSorter {
    public static void sortItems(List<Item> items) {
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                // SimpleItem viene primero
                if (item1 instanceof SimpleItem && item2 instanceof ExpandableItem) {
                    return -1; // item1 viene antes que item2
                } else if (item1 instanceof ExpandableItem && item2 instanceof SimpleItem) {
                    return 1; // item1 viene después que item2
                }
                return 0; // Si son del mismo tipo, no cambian de orden
            }
        });
    }
}
