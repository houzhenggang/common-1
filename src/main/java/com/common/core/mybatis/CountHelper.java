package com.common.core.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.common.core.logging.Logger;
import com.common.core.logging.LoggerFactory;

public final class CountHelper {
    private static Logger logger = LoggerFactory.getLogger(CountHelper.class);

    private static final ThreadLocal<Integer> totalRowLocals = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return Integer.valueOf(0);
        }
    };

    public static void setCount(int count) {
        totalRowLocals.set(Integer.valueOf(count));
    }

    public static void getCount(Connection connection, String sql, Object parameterObject, BoundSql countBoundSql, MappedStatement ms)
            throws Exception {
        DefaultParameterHandler handler = new DefaultParameterHandler(ms, parameterObject, countBoundSql);
        ResourceTracker tracker = new ResourceTracker("Total Count");
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            tracker.attach(ps);
            handler.setParameters(ps);
            ResultSet rs = ps.executeQuery();
            tracker.attach(rs);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Total count [{}], sql [{}]", new Object[] { Integer.valueOf(count), sql });
            }
            totalRowLocals.set(Integer.valueOf(count));
        } catch (Exception e) {
            totalRowLocals.set(Integer.valueOf(0));
            throw e;
        } finally {
            tracker.clear();
        }
    }

    public static int getTotalRow() {
        try {
            return totalRowLocals.get().intValue();
        } finally {
            totalRowLocals.remove();
        }
    }
}
