package com.example.campodetiro.ui.home.recycler.transformer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SubItem {
    boolean ignore() default false;
    String name() default "";
    boolean primary() default false;
    boolean modifiable() default false;
}
