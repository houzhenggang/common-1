package com.common.core.logging.jdk;

import com.common.core.logging.Journal;
import com.common.core.logging.LoggerFactoryDelegate;

public class JulLoggerFactoryDelegate implements LoggerFactoryDelegate {

    public Journal getLogger(String name) {
        return new JulLogger(name);
    }

    public String toString() {
        return "Java Util Logging";
    }
}
