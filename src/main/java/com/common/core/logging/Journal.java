package com.common.core.logging;

import java.util.Date;

public abstract class Journal {
    private static final ThreadLocal<LoggingEvent> EVENT_LOCAL = new ThreadLocal<LoggingEvent>();

    protected final String buildMessage() {
        LoggingEvent event = (LoggingEvent) EVENT_LOCAL.get();
        if (event == null) {
            return "";
        }
        StringBuilder buf = new StringBuilder(80);
        buf.append(event.getMethod()).append(event.getMessage());
        buf.append(DateFormatUtils.format(new Date(event.getTimestamp())));
        return buf.toString();
    }

    void error0(LoggingEvent event) {
        EVENT_LOCAL.set(event);
        if (event.getThrowable() == null) {
            error();
        } else {
            error(event.getThrowable());
        }
    }

    void warn0(LoggingEvent event) {
        EVENT_LOCAL.set(event);
        if (event.getThrowable() == null) {
            warn();
        } else {
            warn(event.getThrowable());
        }
    }

    void info0(LoggingEvent event) {
        EVENT_LOCAL.set(event);
        if (event.getThrowable() == null) {
            info();
        } else {
            info(event.getThrowable());
        }
    }

    void debug0(LoggingEvent event) {
        EVENT_LOCAL.set(event);
        if (event.getThrowable() == null) {
            debug();
        } else {
            debug(event.getThrowable());
        }
    }

    protected abstract void error();

    protected abstract void warn();

    protected abstract void info();

    protected abstract void debug();

    protected abstract void error(Throwable paramThrowable);

    protected abstract void warn(Throwable paramThrowable);

    protected abstract void info(Throwable paramThrowable);

    protected abstract void debug(Throwable paramThrowable);

    protected abstract boolean isDebugEnabled();

    protected abstract boolean isInfoEnabled();

    protected abstract boolean isWarnEnabled();

    protected abstract boolean isErrorEnabled();
}
