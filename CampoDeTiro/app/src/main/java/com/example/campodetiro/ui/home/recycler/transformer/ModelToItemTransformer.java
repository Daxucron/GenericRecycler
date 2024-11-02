package com.example.campodetiro.ui.home.recycler.transformer;

import com.example.campodetiro.ui.home.recycler.ExpandableItem;
import com.example.campodetiro.ui.home.recycler.Item;
import com.example.campodetiro.ui.home.recycler.SimpleItem;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelToItemTransformer {

    private Object obj;
    private String tag;

    public ModelToItemTransformer(Object obj, String tag)
    {
        this.obj = obj;
        this.tag = tag;
    }

    public ModelToItemTransformer(Object obj)
    {
        this.obj = obj;
        this.tag = "";
    }

    public Item transform()
    {
        Item item = null;
        if(checkObject(obj))
        {
            if(obj.getClass().isAnnotationPresent(RecyclerItem.class))
            {
                item = itemRecyclerToListItems();
            }
            else if(obj.getClass().isPrimitive())
            {
                item = new SimpleItem();
                item.setTag(tag);
                item.setValue(obj.toString());

            }
            // WARNING: Recursivo
            else if(obj instanceof Iterable){
                item = itemIterableToListItems();
            }
            else
            {
                item = new SimpleItem();
                item.setTag(tag);
                item.setValue(obj.toString());
            }
        }
        return item;
    }

    private boolean checkObject(Object object)
    {
        return object != null;
    }

    private Item itemIterableToListItems()
    {
        ExpandableItem item = new ExpandableItem();
        List<Item> subItems = new ArrayList<>();
        item.setTag(tag);

        Iterable<?> iterable = (Iterable<?>) obj;
        for(Object i: iterable){
            subItems.add(new ModelToItemTransformer(i).transform());
        }
        ItemSorter.sortItems(subItems);
        item.setChildItems(subItems);
        return item;
    }

    private void getPrimaryName(ExpandableItem expandableItem)
    {
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            addFieldToItem(field,expandableItem);
        }
    }

    // Add the primary value on a exandable intem to identify
    private void addFieldToItem(Field field, ExpandableItem expandableItem) {
        SubItem subItemAnnotation = field.getAnnotation(SubItem.class);
        if(subItemAnnotation!=null && subItemAnnotation.primary()){
            try {
                Object value = ReflexPe.getFieldValue(field,obj);
                if(value != null){
                    SubItem annotation = field.getAnnotation(SubItem.class);
                    if(annotation!=null && !annotation.name().isEmpty()){
                        expandableItem.setTag(annotation.name());
                    }
                    else {
                        expandableItem.setTag(field.getName());
                    }
                    expandableItem.setValue((String) value);
                }

            } catch (IllegalAccessException e) {
                return;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    // TODO: Reducir el código moviedo partes a otros métodos
    private Item itemRecyclerToListItems()
    {
        ExpandableItem expandableItem = new ExpandableItem();
        getPrimaryName(expandableItem);
        List<Item> items = new ArrayList<>();
        Class<?> objClass = obj.getClass();
        Field[] fields = objClass.getDeclaredFields();

        for (Field field : fields) {
            SubItem subItemAnnotation = field.getAnnotation(SubItem.class);
            if(subItemAnnotation==null || !subItemAnnotation.ignore()){
                try {
                    Object value = ReflexPe.getFieldValue(field, obj);
                    String tag;
                    if(value!=null){
                        if(subItemAnnotation!=null&&!subItemAnnotation.name().isEmpty()){
                            tag = subItemAnnotation.name();
                        }
                        else {
                            tag = field.getName();
                        }
                        Item item = new ModelToItemTransformer(value, tag).transform();
                        if(item instanceof SimpleItem && subItemAnnotation!= null && subItemAnnotation.modifiable()){
                            item = ((SimpleItem) item).instantiateModifiableItem(field,obj);
                        }
                        if(field.getType().isAnnotationPresent(RecyclerItem.class)){
                            item.setTag(field.getName());
                        }
                        items.add(item);
                    }


                }
                catch (IllegalAccessException e) {

                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
        Collections.reverse(items);
        ItemSorter.sortItems(items);
        expandableItem.setChildItems(items);
        return expandableItem;
    }
}

