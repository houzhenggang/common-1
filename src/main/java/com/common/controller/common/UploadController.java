/*
 * @(#)UploadController.java 2018年5月23日
 * 
 * Copyright (c), 2018-2020 赵名阳（shining everyday.）
 * 
 * 著作权人保留一切权利，任何使用需经授权。
 */
package com.common.controller.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.constant.BaseUrl;
import com.common.core.controller.BaseController;
import com.common.util.RandomUtil;
import com.common.util.UploadHelper;

/**
 *
 * @author Administrator
 * @date 2018年5月23日 下午4:20:53
 * @version V1.0.0 description：
 * 有问题 弃用
 */
@Controller
@RequestMapping("/common")
public class UploadController extends BaseController {

	@RequestMapping(value = BaseUrl.UPLOAD_MANY, method = RequestMethod.POST)
	@ResponseBody
	public Object uploadMany(HttpServletRequest request){
		String fileName = UploadHelper.uploadManyFiles(request, "goods");
		return fileName;
	}
	
	
	@RequestMapping(value = BaseUrl.UPLOAD, method = RequestMethod.POST)
	@ResponseBody
	@Deprecated
	public Object upload(HttpServletRequest request, String path) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();

		// 图片保存目录路径
		String savePath = request.getServletContext().getRealPath("/") + path + "/";
		// 图片保存目录URL
		String saveUrl = request.getContextPath() + "/" + path + "/";

		if (!ServletFileUpload.isMultipartContent(request)) {
			map.put("error", "请选择图片。");
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
			return jsonObject;
		}

		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		String newFileName = "";

		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				if (!item.isFormField()) {
					// 获取高和宽
					BufferedImage img = javax.imageio.ImageIO.read(item.getInputStream());
					if (img == null) {
						map.put("error", "上传图片不合法。");
						JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
						return jsonObject;
					} else {
						int width = img.getWidth();
						int height = img.getHeight();
						if (width == 0 || height == 0) {
							map.put("error", "上传图片不合法。");
							JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
							return jsonObject;
						}
					}

					// 检查图片大小 最大图片大小2M (2*1024*1024)
					long maxSize = 2097152;
					if (item.getSize() > maxSize) {
						map.put("error", "请上传大小在2M之内的banner图片。");
						JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
						return jsonObject;
					}

					// 检查扩展名
					String fileExtLimit = "gif,jpg,jpeg,png,bmp";
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					if (!Arrays.<String> asList(fileExtLimit.split(",")).contains(fileExt)) {
						map.put("error", "上传图片扩展名是不允许的扩展名。");
						JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
						return jsonObject;
					}

					SimpleDateFormat df = new SimpleDateFormat("HHmmss");
					newFileName = RandomUtil.generateString(8) + df.format(new Date()) + new Random().nextInt(1000)
							+ "." + fileExt;
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**************** 防止上传频率太快 start *******************/
		long newTime = new Date().getTime();
		String oldTime = (String) session.getAttribute("uploadOldTime");
		if (oldTime != null && !"".equals(oldTime)) {
			if (newTime - Long.parseLong(oldTime) < 10 * 1000) { // 20秒
				map.put("error", "上传频率太快了，休息一下再操作吧。");
				JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
				return jsonObject;
			}
		}
		session.setAttribute("uploadOldTime", newTime + "");
		/**************** 防止上传频率太快 end *******************/
		String fileFullPath = saveUrl + newFileName;
		map.put("fileName", fileFullPath.replaceFirst("/", ""));
		map.put("preFileName", "" + fileFullPath.replaceFirst("/", ""));
		JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(map));
		return jsonObject;
	}
	
}
