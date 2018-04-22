package com.common.core.logging;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.common.core.logging.jdk.JulLoggerFactoryDelegate;

class JournalHolder {
    private static final ConcurrentMap<String, Journal> JOURNALS = new ConcurrentHashMap<String, Journal>();
    private static LoggerFactoryDelegate loggerFactoryDelegate;

    static {
        String cname = null;
        try {
            Class.forName("org.slf4j.impl.StaticLoggerBinder");
            cname = "ooh.bravo.logging.slf4j.Slf4JLoggerFactoryDelegate";
        } catch (Throwable ex) {
        }
        if (cname == null) {
            try {
                Class.forName("org.apache.log4j.LogManager");
                cname = "ooh.bravo.logging.log4j.Log4JLoggerFactoryDelegate";
            } catch (Throwable ex) {
            }
        }
        if (cname == null) {
            try {
                Class.forName("org.apache.commons.logging.LogFactory");
                cname = "ooh.bravo.logging.jcl.JclLoggerFactoryDelegate";
            } catch (Throwable ex) {
            }
        }
        try {
            if (cname != null) {
                Class<?> clazz = Class.forName(cname, true, Thread.currentThread().getContextClassLoader());
                loggerFactoryDelegate = (LoggerFactoryDelegate) clazz.newInstance();
            } else {
                fallbackToDefault();
            }
        } catch (Throwable ex) {
            fallbackToDefault();
        }
    }

    public static Journal getLogger(String name) {
        Journal journal = (Journal) JOURNALS.get(name);
        if (journal == null) {
            journal = loggerFactoryDelegate.getLogger(name);
            Journal prev = (Journal) JOURNALS.putIfAbsent(name, journal);
            if (prev != null) {
                journal = prev;
            }
        }
        return journal;
    }

    private static void fallbackToDefault() {
        loggerFactoryDelegate = new JulLoggerFactoryDelegate();
    }
}
