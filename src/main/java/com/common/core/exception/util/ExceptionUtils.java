package com.common.core.exception.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.common.core.exception.BaseException;
import com.common.core.exception.FastCheckedException;
import com.common.core.exception.FastRuntimeException;

public class ExceptionUtils extends org.apache.commons.lang3.exception.ExceptionUtils {
    public static boolean isFastException(Throwable e) {
        return (FastCheckedException.class.isInstance(e)) || (FastRuntimeException.class.isInstance(e));
    }

    public static boolean isCheckedException(Throwable e) {
        return ((e instanceof Exception)) && (!isRuntimeException(e));
    }

    public static boolean isRuntimeException(Throwable e) {
        return e instanceof RuntimeException;
    }

    public static boolean isHandledException(Throwable e) {
        List<BaseException> bes = findAllBaseException(e);
        for (BaseException be : bes) {
            if (be.isHandled()) {
                return true;
            }
        }
        return false;
    }

    private static List<BaseException> findAllBaseException(Throwable e) {
        List<BaseException> bes = new ArrayList<BaseException>();
        Throwable[] throwables = getThrowables(e);
        for (Throwable t : throwables) {
            if ((t instanceof BaseException)) {
                bes.add((BaseException) t);
            }
        }
        return bes;
    }

    public static String toString(Throwable e) {
        return toString("", e);
    }

    public static String toString(String msg, Throwable e) {
        StringWriter w = new StringWriter();
        w.write(msg);
        PrintWriter p = new PrintWriter(w);
        p.println();
        try {
            e.printStackTrace(p);
            return w.toString();
        } finally {
            p.close();
        }
    }

    public static Throwable getRootCause(Throwable e) {
        for (;;) {
            if (e.getCause() == null) {
                return e;
            }
            e = e.getCause();
        }
    }

    // public static <T extends Throwable> T findCause(Throwable e, Class<T>
    // klass) {
    // while ((e != null) && (!klass.isInstance(e))) {
    // e = e.getCause();
    // }
    // return null;
    // }
    //
    // public static <T extends Throwable> boolean causedBy(Throwable e,
    // Class<T> klass) {
    // return findCause(e, klass) != null;
    // }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static String[] convertArgsToString(Object[] args) {
        String[] argsStrs = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            argsStrs[i] = String.valueOf(args[i]);
        }
        return argsStrs;
    }
}
