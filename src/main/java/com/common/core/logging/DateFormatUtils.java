package com.common.core.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

class DateFormatUtils {
    private static final String HH_MM_SS_SSS = "HH:mm:ss.SSS";
    private static final ThreadLocal<SimpleDateFormat> locals = new ThreadLocal<SimpleDateFormat>();

    public static String format(Date d) {
        return getDateFormat().format(d);
    }

    private static SimpleDateFormat getDateFormat() {
        SimpleDateFormat df = locals.get();
        if (df == null) {
            df = new SimpleDateFormat(HH_MM_SS_SSS);
            locals.set(df);
        }
        return df;
    }
}
