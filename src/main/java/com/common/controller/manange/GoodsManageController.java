/*
 * @(#)GoodsManageController.java 2018年5月26日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.manange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.command.GoodsCommand;
import com.common.command.NoticeCommand;
import com.common.constant.ManageUrl;
import com.common.constant.StatusCode;
import com.common.core.controller.BaseController;
import com.common.dto.GoodsDTO;
import com.common.pojo.Category;
import com.common.pojo.Goods;
import com.common.pojo.Notice;
import com.common.service.base.UserService;
import com.common.service.manage.CategoryManageService;
import com.common.service.manage.GoodsManageService;
import com.common.transfer.GoodsTransfer;
import com.common.util.FileAddress;
import com.common.util.NewDate;
import com.common.util.UploadHelper;

/**
 *
 * @author Administrator
 * @date 2018年5月26日 下午5:13:07
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/manage")
public class GoodsManageController extends BaseController{

	@Autowired
	private GoodsManageService goodsManageService;
	
	@Autowired
	private CategoryManageService categoryManageService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 商品列表
	 * @param model
	 * @param request
	 */
    @RequestMapping(value = ManageUrl.GOODS_LIST)
    //@RequiresPermissions("notice:manage")
    public String list(Model model, HttpServletRequest request) {
    	List<Goods> listGoods = goodsManageService.getAllGoods();
    	HttpSession session = request.getSession();
    	session.setAttribute("listGoods", listGoods);
    	return "goods/list";
    }
	
    /**
     * 新增商品页面
     * @param model
     * @param command
     * @return
     */
	@RequestMapping(value = ManageUrl.GOODS_ADD, method = RequestMethod.GET)
    //@RequiresPermissions("notice:add")
	public String addForm(Model model, @ModelAttribute GoodsCommand goodsCommand){
		List<Category> listCategory = categoryManageService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		return "goods/add";
	}

    /**
     * 新增商品
     * @param model
     * @param command
     * @return
     */
    @RequestMapping(value = ManageUrl.GOODS_ADD, method = RequestMethod.POST)
    //@RequiresPermissions("banner:add")
    public String add(Model model, HttpServletRequest request, @ModelAttribute GoodsCommand goodsCommand){
    	if (goodsCommand.getName() == null || "".equals(goodsCommand.getName())) {
    		model.addAttribute("error", "请输入商品名称！");
    		return addForm(model, goodsCommand);
    	}
    	
    	if (goodsCommand.getPrice() == null || goodsCommand.getPrice() == 0) {
    		model.addAttribute("error", "请输入商品价格！");
    		return addForm(model, goodsCommand);
    	}
    	
    	if (goodsCommand.getCategoryId() == null || goodsCommand.getCategoryId() == 0L) {
    		model.addAttribute("error", "请输入商品类别！");
    		return addForm(model, goodsCommand);
    	}
    	
    	String coverImg = UploadHelper.uploadFile(request, "img", "goods");
    	String creator = userService.getCurrentUser().getUsername();
    	goodsManageService.createGoods(goodsCommand, creator, coverImg);
    	/*try {
			logger.info("删除图片结果{}", UploadHelper.deleteByFileName(coverImg));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    	return "redirect:/manage/goods/list";
    }

    /**
     * 修改商品页面
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.GOODS_EDIT, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String editForm(Model model, @PathVariable Long id){
		Goods goods = goodsManageService.getGoods(id);
		if (goods == null) {
			return "redirect:/manage/goods/list";
		}
		GoodsDTO goodsDTO = GoodsTransfer.transferGoodsDTO(goods);
		List<Category> listCategory = categoryManageService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("id", id);
		model.addAttribute("goods", goodsDTO);
		return "goods/edit";
	}
    
	@RequestMapping(value = ManageUrl.GOODS_EDIT, method = RequestMethod.POST)
    //@RequiresPermissions("banner:edit")
	public String edit(Model model, HttpServletRequest request, @PathVariable Long id, 
			@ModelAttribute GoodsCommand goodsCommand){
		
		if (goodsCommand.getName() == null || "".equals(goodsCommand.getName())) {
    		model.addAttribute("error", "商品名称不能为空，已恢复至修改之前状态！");
    		return editForm(model,id);
    	}
    	
    	if (goodsCommand.getPrice() == null || goodsCommand.getPrice() == 0) {
    		model.addAttribute("error", "商品价格不能为空，已恢复至修改之前状态！");
    		return editForm(model,id);
    	}
    	
    	if (goodsCommand.getCategoryId() == null || goodsCommand.getCategoryId() == 0L) {
    		model.addAttribute("error", "商品类别不能为空，已恢复至修改之前状态！");
    		return editForm(model,id);
    	}
		
		Goods goods = goodsManageService.getGoods(id);
		if (goods == null) {
			return "redirect:/manage/goods/list";
		}
		
		String coverImg = UploadHelper.uploadFile(request, "img", "goods");
		if (coverImg != null && !"".equals(coverImg)) {
			goods.setCoverImg(coverImg);
		}
		goods.setCreator(userService.getCurrentUser().getUsername());
		goodsManageService.updateGoods(goods, goodsCommand);
		return "redirect:/manage/goods/list";
	}
	
	/**
     * 查看商品
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.GOODS_VIEW, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String view(Model model, @PathVariable Long id){
		Goods goods = goodsManageService.getGoods(id);
		if (goods == null) {
			return "redirect:/manage/goods/list";
		}
		GoodsDTO goodsDTO = GoodsTransfer.transferGoodsDTO(goods);
		List<Category> listCategory = categoryManageService.getAllCategory();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("goods", goodsDTO);
		return "goods/view";
	}	
	
	 /**
	  * 删除商品
	  * @param model
	  * @param id
	  * @return
	  */
	@RequestMapping(value = ManageUrl.GOODS_DELETE, method = RequestMethod.GET)
	//@RequiresPermissions("banner:delete")
	public String delete(Model model,@PathVariable Long id){
		Goods goods = goodsManageService.getGoods(id);
		if (goods == null) {
			return "redirect:/manage/goods/list";
		}
		goodsManageService.deleteGoods(id);
		return "redirect:/manage/goods/list";
	}
	
	
	/**
	 * 上架/下架商品
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = ManageUrl.GOODS_CHANGE, method = RequestMethod.POST)
	@ResponseBody
	//@RequiresPermissions("banner:change")
	public Object changeStatus(Model model, @PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Goods goods = goodsManageService.getGoods(id);
		if(goods != null){
	    	//状态【0：不滚动】
			if (goods.getStatus() == 0) {
				goods.setStatus(StatusCode.STATUS_ON.getCode());
				goods.setUpdateTime(NewDate.getDateTime());
				goodsManageService.updateGoods(goods);
			} else {
				goods.setStatus(StatusCode.STATUS_OFF.getCode());
				goods.setUpdateTime(NewDate.getDateTime());
				goodsManageService.updateGoods(goods);
			}
		}
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
		return jsonObject;
		
	}
	
}
