package com.common.core.logging;

import com.common.core.logging.annotation.Level;

class LoggingEvent {

    private final Level level;
    private final String name;
    private final String method;
    private final String message;
    private final Throwable throwable;
    private final Object[] arguments;
    private final long timestamp = System.currentTimeMillis();

    public LoggingEvent(String name, String method, Level level, String message) {
        this.name = name;
        this.method = method;
        this.level = level;
        this.message = nullToEmpty(message);
        this.throwable = null;
        this.arguments = null;
    }

    public LoggingEvent(String name, String method, Level level, String message, Throwable throwable) {
        this.name = name;
        this.method = method;
        this.level = level;
        this.message = nullToEmpty(message);
        this.throwable = throwable;
        this.arguments = null;
    }

    public LoggingEvent(String name, String method, Level level, String message, Throwable throwable, Object... arguments) {
        this.name = name;
        this.method = method;
        this.level = level;
        this.message = nullToEmpty(message);
        this.throwable = throwable;
        this.arguments = arguments;
    }

    private String nullToEmpty(String message) {
        return message == null ? "" : message;
    }

    public Level getLevel() {
        return this.level;
    }

    public String getMessage() {
        return this.message;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public String getName() {
        return this.name;
    }

    public String getMethod() {
        return this.method;
    }

    public Object[] getArguments() {
        return this.arguments;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}
