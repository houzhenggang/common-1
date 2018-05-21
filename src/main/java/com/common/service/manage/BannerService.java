/*
 * @(#)BannerService.java 2018年5月21日
 * 
 * Copyright (c), 2017 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.service.manage;

import java.util.List;

import com.common.command.BannerCommand;
import com.common.pojo.Banner;


/**
 *
 * @author Administrator
 * @date 2018年5月21日 下午3:22:23
 * @version V1.0.0
 * description：
 * 
 */
public interface BannerService {

	Banner getBanner(int id);

    void createBanner(BannerCommand bannerCommand, String imgPath, String creator);

    void deleteBanner(int id);

    void updateBanner(Banner banner, BannerCommand bannerCommand);
    
    List<Banner> getAllBanner();
    
    int countRollingBanner();
	
}
