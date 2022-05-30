package com.asdasd.mjeesh.store.exception;

import java.util.Arrays;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, Object... searchParam) {
        super(generateMessage(clazz.getSimpleName(), searchParam));
    }

    private static String generateMessage(String entity, Object[] searchParams) {
        return entity + " not found for parameters: " + Arrays.toString(searchParams);
    }
}
