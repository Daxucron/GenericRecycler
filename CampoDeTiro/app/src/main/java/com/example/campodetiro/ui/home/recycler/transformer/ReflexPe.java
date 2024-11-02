package com.example.campodetiro.ui.home.recycler.transformer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflexPe {

    public static Object getFieldValue(Field field, Object object) throws Exception {
        String getterName = buildGetterName(field);
        Method getterMethod = object.getClass().getMethod(getterName);
        return getterMethod.invoke(object);
    }

    private static String buildGetterName(Field field) {
        String fieldName = field.getName();
        String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        if(field.getType() == boolean.class){
            return "is" + capitalizedFieldName;
        }
        return "get" + capitalizedFieldName;
    }

    public static void setFieldValue(Field field, Object object, Object value) throws Exception {
        String setterName = buildSetterName(field.getName());
        Method setterMethod = object.getClass().getMethod(setterName, field.getType());
        setterMethod.invoke(object, value);
    }

    private static String buildSetterName(String fieldName) {
        String capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        return "set" + capitalizedFieldName;
    }

}
