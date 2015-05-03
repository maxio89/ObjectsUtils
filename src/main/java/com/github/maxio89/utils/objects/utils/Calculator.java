package com.github.maxio89.utils.objects.utils;

import javax.annotation.Nullable;
import java.util.List;

public class Calculator<T extends Summable<T>> {


    /**
     * Method just sums a list of objects of type {@link Summable}.
     *
     * @param objects is the list of objects which will be added together.
     * @return the new object which is the sum of given objects or null if given objects are empty.
     * @throws IllegalArgumentException when given objects are null.
     */
    @Nullable
    public T sum(@Nullable final List<T> objects) {
        if (null == objects) {
            throw new IllegalArgumentException("Ups, list of objects cannot be null!");
        }
        T resultObject = null;
        for (T object : objects) {
            resultObject = object.add(resultObject);
        }
        return resultObject;
    }


}
