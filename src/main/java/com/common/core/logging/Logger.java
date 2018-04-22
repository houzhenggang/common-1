package com.common.core.logging;

public interface Logger {
    public void error(String paramString);

    public void warn(String paramString);

    public void info(String paramString);

    public void debug(String paramString);

    public void error(String paramString, Throwable paramThrowable);

    public void warn(String paramString, Throwable paramThrowable);

    public void info(String paramString, Throwable paramThrowable);

    public void debug(String paramString, Throwable paramThrowable);

    public void error(String paramString, Object... paramVarArgs);

    public void warn(String paramString, Object... paramVarArgs);

    public void info(String paramString, Object... paramVarArgs);

    public void debug(String paramString, Object... paramVarArgs);

    public void catching(Throwable paramThrowable);

    public <T extends Throwable> T throwing(T paramT);

    public boolean isDebugEnabled();

    public boolean isInfoEnabled();

    public boolean isWarnEnabled();

    public boolean isErrorEnabled();
}
