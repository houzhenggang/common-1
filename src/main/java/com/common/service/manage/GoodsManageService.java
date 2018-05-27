/*
 * @(#)GoodsService.java 2018年5月27日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage;

import java.util.List;

import com.common.command.GoodsCommand;
import com.common.pojo.Goods;

/**
 *
 * @author Administrator
 * @date 2018年5月27日 下午5:24:17
 * @version V1.0.0
 * description：
 * 
 */
public interface GoodsManageService {

	Goods getGoods(Long id);

    void createGoods(GoodsCommand goodsCommand, String creator, String coverImg);

    void deleteGoods(Long id);

    void updateGoods(Goods goods);
    
    void updateGoods(Goods goods, GoodsCommand goodsCommand);
    
    List<Goods> getAllGoods();
	
}
