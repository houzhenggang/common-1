package com.common.util;

import org.apache.ibatis.session.RowBounds;

import com.common.core.dto.PageRequestDTO;

/**
 *
 * @author zhaomy07
 * @date 2017年10月11日 上午10:05:13
 * @version V1.0.0
 * description：
 */
public class RowBoundsUtils {

    public static RowBounds getRowBounds(int pageNum,int pageSize) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        return new RowBounds((pageNum - 1) * pageSize, pageSize);
    }
    
    public static RowBounds getRowBounds(PageRequestDTO<?> pageParam) {
		if (pageParam == null) {
			return null;
		}
		int pageNum = pageParam.getPageNum();
		if (pageNum <= 0) {
			pageNum = 1;
		}
		int pageSize = pageParam.getPageSize();
		if (pageSize <= 0) {
			pageSize = 20;
		}
		return new RowBounds((pageNum - 1) * pageSize, pageSize);
	}
    
}
