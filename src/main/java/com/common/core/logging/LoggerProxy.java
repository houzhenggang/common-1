package com.common.core.logging;

import com.common.core.logging.annotation.Level;
import com.common.core.logging.util.FormattingTuple;
import com.common.core.logging.util.MessageFormatter;

class LoggerProxy implements Logger {
    private static final Journal logger = JournalHolder.getLogger(LoggerProxy.class.getName());
    private final String name;

    public LoggerProxy(String name) {
        this.name = name;
    }

    private void logging(LoggingEvent event) {
        Journal journal = JournalHolder.getLogger(event.getName());
        switch (event.getLevel()) {
        case INFO:
            journal.info0(event);
            break;
        case ERROR:
            journal.error0(event);
            break;
        case WARN:
            journal.warn0(event);
            break;
        case DEBUG:
        default:
            journal.debug0(event);
        }
    }

    public void error(String format, Object... arguments) {
        logging(buildEvent(Level.ERROR, format, arguments));
    }

    public void warn(String format, Object... arguments) {
        logging(buildEvent(Level.WARN, format, arguments));
    }

    public void info(String format, Object... arguments) {
        logging(buildEvent(Level.INFO, format, arguments));
    }

    public void debug(String format, Object... arguments) {
        logging(buildEvent(Level.DEBUG, format, arguments));
    }

    private LoggingEvent buildEvent(Level level, String format, Object[] arguments) {
        FormattingTuple fmt = MessageFormatter.arrayFormat(format, arguments);
        return new LoggingEvent(this.name, method(), level, fmt.getMessage(), fmt.getThrowable(), arguments);
    }

    public void catching(Throwable throwable) {
        error("catching", throwable);
    }

    public <T extends Throwable> T throwing(T throwable) {
        error("throwing", throwable);
        return throwable;
    }

    public void error(String message) {
        logging(buildEvent(Level.ERROR, message));
    }

    public void warn(String message) {
        logging(buildEvent(Level.WARN, message));
    }

    public void info(String message) {
        logging(buildEvent(Level.INFO, message));
    }

    public void debug(String message) {
        logging(buildEvent(Level.DEBUG, message));
    }

    private LoggingEvent buildEvent(Level level, String message) {
        return new LoggingEvent(this.name, method(), level, message);
    }

    public void error(String message, Throwable error) {
        logging(buildEvent(Level.ERROR, message, error));
    }

    public void warn(String message, Throwable error) {
        logging(buildEvent(Level.WARN, message, error));
    }

    public void info(String message, Throwable error) {
        logging(buildEvent(Level.INFO, message, error));
    }

    public void debug(String message, Throwable error) {
        logging(buildEvent(Level.DEBUG, message, error));
    }

    private LoggingEvent buildEvent(Level level, String message, Throwable error) {
        return new LoggingEvent(this.name, method(), level, message, error);
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    private String method() {
        return "";
    }
}
