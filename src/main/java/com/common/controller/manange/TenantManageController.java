/*
 * @(#)TenantManageController.java 2018年5月25日
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
import com.common.command.TenantCommand;
import com.common.constant.ManageUrl;
import com.common.constant.StatusCode;
import com.common.core.controller.BaseController;
import com.common.pojo.Notice;
import com.common.pojo.Tenant;
import com.common.service.base.UserService;
import com.common.service.manage.TenantManageService;
import com.common.util.NewDate;
import com.common.util.UploadHelper;

/**
 *
 * @author Administrator
 * @date 2018年5月25日 下午2:19:41
 * @version V1.0.0
 * description：
 * 
 */
@Controller
@RequestMapping("/manage")
public class TenantManageController extends BaseController{

	@Autowired
	private TenantManageService tenantManageService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 租户列表
	 * @param model
	 * @param request
	 */
    @RequestMapping(value = ManageUrl.TENANT_LIST)
    //@RequiresPermissions("notice:manage")
    public String list(Model model, HttpServletRequest request) {
    	List<Tenant> listTenant = tenantManageService.getAllTenant();
    	HttpSession session = request.getSession();
    	session.setAttribute("listTenant", listTenant);
    	return "tenant/list";
    }
	
    /**
     * 新增租户页面
     * @param model
     * @param command
     * @return
     */
	@RequestMapping(value = ManageUrl.TENANT_ADD, method = RequestMethod.GET)
    //@RequiresPermissions("notice:add")
	public String addForm(Model model, @ModelAttribute TenantCommand tenantCommand){
		return "tenant/add";
	}
	
    /**
     * 新增租户
     * @param model
     * @param command
     * @return
     */
    @RequestMapping(value = ManageUrl.TENANT_ADD, method = RequestMethod.POST)
    //@RequiresPermissions("banner:add")
    public String add(Model model, HttpServletRequest request, @ModelAttribute TenantCommand tenantCommand){
    	if (tenantCommand.getName() == null || "".equals(tenantCommand.getName())) {
    		model.addAttribute("error", "请输入租户名称！");
    		return addForm(model, tenantCommand);
    	}
    	String imgPath = UploadHelper.uploadFile(request, "img", "tenant");
    	String creator = userService.getCurrentUser().getUsername();
    	tenantManageService.createTenant(tenantCommand, creator, imgPath);
    	return "redirect:/manage/tenant/list";
    }
    
    /**
     * 修改租户页面
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.TENANT_EDIT, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String editForm(Model model, @PathVariable Long id){
		Tenant tenant = tenantManageService.getTenant(id);
		if (tenant == null) {
			return "redirect:/manage/tenant/list";
		}
		model.addAttribute("id", id);
		model.addAttribute("tenant", tenant);
		return "tenant/edit";
	}
	
	/**
	 * 修改租户
	 * @param model
	 * @param request
	 * @param id
	 * @param noticeCommand
	 * @return
	 */
	@RequestMapping(value = ManageUrl.TENANT_EDIT, method = RequestMethod.POST)
    //@RequiresPermissions("banner:edit")
	public String edit(Model model, HttpServletRequest request, @PathVariable Long id, 
			@ModelAttribute TenantCommand tenantCommand){
		
		if (tenantCommand.getName() == null || "".equals(tenantCommand.getName())) {
    		model.addAttribute("error", "租户名称不能为空，已恢复至修改之前状态！！");
    		return editForm(model,id);
    	}
		
		Tenant tenant = tenantManageService.getTenant(id);
		if (tenant == null) {
			return "redirect:/manage/tenant/list";
		}
		
		String imgPath = UploadHelper.uploadFile(request, "img", "tenant");
		
		if (imgPath != null && !"".equals(imgPath)) {
			tenant.setPhoto(imgPath);
		}
		tenant.setCreator(userService.getCurrentUser().getUsername());
		
		tenantManageService.updateTenant(tenant, tenantCommand);
		return "redirect:/manage/tenant/list";
	}    
    
	/**
     * 查看租户
     * @param model
     * @param id
     * @return
     */
	@RequestMapping(value = ManageUrl.TENANT_VIEW, method = RequestMethod.GET)
    //@RequiresPermissions("banner:edit")
	public String view(Model model, @PathVariable Long id){
		Tenant tenant = tenantManageService.getTenant(id);
		if (tenant == null) {
			return "redirect:/manage/tenant/list";
		}
		model.addAttribute("tenant", tenant);
		return "tenant/view";
	}	
	
	 /**
	  * 删除租户
	  * @param model
	  * @param id
	  * @return
	  */
	@RequestMapping(value = ManageUrl.TENANT_DELETE, method = RequestMethod.GET)
	//@RequiresPermissions("banner:delete")
	public String delete(Model model,@PathVariable Long id){
		Tenant tenant = tenantManageService.getTenant(id);
		if (tenant == null) {
			return "redirect:/manage/tenant/list";
		}
		tenantManageService.deleteTenant(id);
		return "redirect:/manage/tenant/list";
	}

	/**
	 * 冻结/解冻租户
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = ManageUrl.TENANT_CHANGE, method = RequestMethod.POST)
	@ResponseBody
	//@RequiresPermissions("banner:change")
	public Object changeStatus(Model model, @PathVariable Long id){
		Map<String, Object> map = new HashMap<String, Object>();
		Tenant tenant = tenantManageService.getTenant(id);
		if(tenant != null){
	    	//状态【0：不滚动】
			if (tenant.getStatus() == 0) {
				tenant.setStatus(StatusCode.STATUS_ON.getCode());
				tenant.setUpdateTime(NewDate.getDateTime());
				tenantManageService.updateTenant(tenant);
			} else {
				tenant.setStatus(StatusCode.STATUS_OFF.getCode());
				tenant.setUpdateTime(NewDate.getDateTime());
				tenantManageService.updateTenant(tenant);
			}
		}
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
		return jsonObject;
		
	}
	
}
