package com.github.maxio89.utils.objects.utils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface Summable<T> {

    @Nonnull
    T add(@Nullable T o1);

}
