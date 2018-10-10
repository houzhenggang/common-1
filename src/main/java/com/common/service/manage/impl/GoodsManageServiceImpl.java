/*
 * @(#)GoodsManageServiceImpl.java 2018年5月27日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.command.GoodsCommand;
import com.common.constant.StatusCode;
import com.common.dao.GoodsDAO;
import com.common.pojo.Goods;
import com.common.service.manage.GoodsManageService;
import com.common.util.NewDate;
import com.common.util.PropertiesUtil;

/**
 *
 * @author Administrator
 * @date 2018年5月27日 下午5:30:37
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class GoodsManageServiceImpl implements GoodsManageService {

	private final static String PREFIX_CODE = PropertiesUtil.loadProperties("config").getProperty("goodsCode");
	
	@Autowired
	private  GoodsDAO goodsDAO;
	
	@Override
	public Goods getGoods(Long id) {
		return goodsDAO.selectByPrimaryKey(id);
	}

	@Override
	public void createGoods(GoodsCommand goodsCommand, String creator, String coverImg) {
		Goods goods = new Goods();
		goods.setName(goodsCommand.getName());
		goods.setKeywords(goodsCommand.getKeywords());
		goods.setPrice(goodsCommand.getPrice());
		goods.setColors(goodsCommand.getColors());
		goods.setLongness(goodsCommand.getLongness());
		goods.setMaterial(goodsCommand.getMaterial());
		goods.setStyle(goodsCommand.getStyle());
		goods.setEnvLevel(goodsCommand.getEnvLevel());
		goods.setCategoryId(goodsCommand.getCategoryId());
		goods.setCoverImg(coverImg);
		goods.setDetail(goodsCommand.getDetail());
		goods.setDetailImgs(goodsCommand.getDetailImgs());
		goods.setStatus(StatusCode.STATUS_ON.getCode());
		goods.setCreator(creator);
		goods.setCreateTime(NewDate.getDateTime());
		goods.setUpdateTime(goods.getCreateTime());
		int result = goodsDAO.insertSelective(goods);
		if (result == 1) {
			goods.setCode(PREFIX_CODE + NewDate.getFormatDate(new Date(), "yyyyMMddHHmmss") + goods.getId());
			goodsDAO.updateByPrimaryKey(goods);
		}
	}

	@Override
	public void deleteGoods(Long id) {
		goodsDAO.deleteByPrimaryKey(id);
	}

	@Override
	public void updateGoods(Goods goods) {
		goodsDAO.updateByPrimaryKeySelective(goods);
	}

	@Override
	public void updateGoods(Goods goods, GoodsCommand goodsCommand) {
		goods.setName(goodsCommand.getName());
		goods.setKeywords(goodsCommand.getKeywords());
		goods.setPrice(goodsCommand.getPrice());
		goods.setColors(goodsCommand.getColors());
		goods.setLongness(goodsCommand.getLongness());
		goods.setMaterial(goodsCommand.getMaterial());
		goods.setStyle(goodsCommand.getStyle());
		goods.setEnvLevel(goodsCommand.getEnvLevel());
		goods.setCategoryId(goodsCommand.getCategoryId());
		goods.setDetail(goodsCommand.getDetail());
		goods.setDetailImgs(goodsCommand.getDetailImgs());
		goods.setStatus(StatusCode.STATUS_ON.getCode());
		goods.setUpdateTime(NewDate.getDateTime());
		goodsDAO.updateByPrimaryKey(goods);
	}

	@Override
	public List<Goods> getAllGoods() {
		return goodsDAO.getAllGoods();
	}

}
