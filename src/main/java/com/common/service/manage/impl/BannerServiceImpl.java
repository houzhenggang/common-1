/*
 * @(#)BannerServiceImpl.java 2018年5月21日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.command.BannerCommand;
import com.common.dao.BannerDAO;
import com.common.pojo.Banner;
import com.common.service.manage.BannerService;

/**
 *
 * @author Administrator
 * @date 2018年5月21日 下午5:50:02
 * @version V1.0.0
 * description：
 * 
 */
@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerDAO bannerDAO;
	
	@Override
	public Banner getBanner(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBanner(BannerCommand bannerCommand, String imgPath, String creator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBanner(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBanner(Banner banner, BannerCommand bannerCommand) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Banner> getAllBanner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countRollingBanner() {
		// TODO Auto-generated method stub
		return 0;
	}

}
