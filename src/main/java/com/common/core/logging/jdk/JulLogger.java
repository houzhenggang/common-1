package com.common.core.logging.jdk;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.common.core.logging.Journal;

public class JulLogger extends Journal {

    private final Logger logger;

    public JulLogger(String name) {
        this.logger = Logger.getLogger(name);
    }

    public void error() {
        this.logger.log(Level.SEVERE, buildMessage());
    }

    public void warn() {
        this.logger.log(Level.WARNING, buildMessage());
    }

    public void info() {
        this.logger.log(Level.INFO, buildMessage());
    }

    public void debug() {
        this.logger.log(Level.FINE, buildMessage());
    }

    public void error(Throwable error) {
        this.logger.log(Level.SEVERE, buildMessage(), error);
    }

    public void warn(Throwable error) {
        this.logger.log(Level.WARNING, buildMessage(), error);
    }

    public void info(Throwable error) {
        this.logger.log(Level.INFO, buildMessage(), error);
    }

    public void debug(Throwable error) {
        this.logger.log(Level.FINE, buildMessage(), error);
    }

    public boolean isDebugEnabled() {
        return this.logger.isLoggable(Level.FINE);
    }

    public boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }

    public boolean isWarnEnabled() {
        return this.logger.isLoggable(Level.WARNING);
    }

    public boolean isErrorEnabled() {
        return this.logger.isLoggable(Level.SEVERE);
    }
}
