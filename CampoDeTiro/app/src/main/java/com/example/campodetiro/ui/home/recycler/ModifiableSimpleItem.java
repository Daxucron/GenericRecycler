package com.example.campodetiro.ui.home.recycler;

import android.util.Log;

import com.example.campodetiro.ui.home.recycler.transformer.ReflexPe;

import java.lang.reflect.Field;

public class ModifiableSimpleItem extends SimpleItem implements Modifiable{

    private Field field;
    private Object object;

    public ModifiableSimpleItem(Field field, Object object){
        this.field = field;
        this.object = object;
    }

    public Field getField() {
        return field;
    }


    public void newValue(String value) {
        try {
            Object nvalue = convertToFieldType(value, getField().getType());
            ReflexPe.setFieldValue(getField(),object,nvalue);
            setValue(nvalue.toString());
        }catch (Exception e){
            Log.d("newValue", "fallo");
        }

    }

    public static Object convertToFieldType(Object value, Class<?> targetType) {
        if (targetType.isAssignableFrom(value.getClass())) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value.toString());
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value.toString());
        } else if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(value.toString());
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value.toString());
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value.toString());
        } else if (targetType == String.class) {
            return value.toString();
        }

        throw new IllegalArgumentException("Tipo no soportado: " + targetType.getName());
    }
}


