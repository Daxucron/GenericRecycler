package com.example.campodetiro.ui.home.recycler.transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RecyclerItem {
    String name() default "";
    boolean ignore() default false;
}