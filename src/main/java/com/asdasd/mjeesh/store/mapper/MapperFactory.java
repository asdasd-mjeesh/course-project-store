package com.asdasd.mjeesh.store.mapper;

import java.util.List;

public interface MapperFactory<T, F> {

    T map(F from);

    List<T> map(List<F> from);
}
