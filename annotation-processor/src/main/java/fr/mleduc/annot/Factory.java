package fr.mleduc.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by mleduc on 29/03/17.
 */
@Target(TYPE)
@Retention(CLASS)
public @interface Factory {
    Class type();

    String id();
}
