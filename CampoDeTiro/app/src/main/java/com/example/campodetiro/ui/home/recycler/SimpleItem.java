package com.example.campodetiro.ui.home.recycler;

import java.lang.reflect.Field;

public class SimpleItem extends Item{

    public SimpleItem() {
        setTag("");
    }
    public SimpleItem(String text) {
        setTag(text);
    }

    public Item instantiateModifiableItem(Field field, Object object){
        ModifiableSimpleItem item =  new ModifiableSimpleItem(field,object);
        item.setTag(getTag());
        item.setValue(getValue());
        return item;
    }
}
