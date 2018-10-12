package com.common.transfer;

import com.common.dto.GoodsDTO;
import com.common.pojo.Goods;

/**
 *
 * @author zhaomingyang9
 * @date 2018年10月11日 下午3:32:32
 * @version V1.0.0
 * description：
 * 
 */
public class GoodsTransfer {

	
	public static GoodsDTO transferGoodsDTO(Goods goods){
		
		if (goods == null) {
			return null;
		}
		GoodsDTO dto = new GoodsDTO();
		dto.setId(goods.getId());
		dto.setName(goods.getName());
		dto.setKeywords(goods.getKeywords());
		dto.setKeywordList(CommonTransferUtil.StringToList(goods.getKeywords()));
		dto.setPrice(goods.getPrice());
		dto.setColors(goods.getColors());
		dto.setColorList(CommonTransferUtil.StringToList(goods.getColors()));
		dto.setLongness(goods.getLongness());
		dto.setMaterial(goods.getMaterial());
		dto.setStyle(goods.getStyle());
		dto.setEnvLevel(goods.getEnvLevel());
		dto.setCategoryId(goods.getCategoryId());
		dto.setCoverImg(goods.getCoverImg());
		dto.setDetail(goods.getDetail());
		dto.setDetailImgs(goods.getDetailImgs());
		dto.setDetailImgList(CommonTransferUtil.StringToList(goods.getDetailImgs()));
		dto.setStatus(goods.getStatus());
		dto.setCreator(goods.getCreator());
		dto.setCreateTime(goods.getCreateTime());
		dto.setUpdateTime(goods.getUpdateTime());
		return dto;
		
	}
	
}
