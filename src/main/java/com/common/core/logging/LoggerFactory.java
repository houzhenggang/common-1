package com.common.core.logging;

public final class LoggerFactory {

    public static Logger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    public static Logger getLogger(String name) {
        return new LoggerProxy(name);
    }
}
