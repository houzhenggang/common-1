package com.common.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.util.UriUtils;


public class InvokeAndEncodeHelper {

	/**
	 * 根据实例获取类名称（不包括包名，首字母小写）
	 * 
	 * @param obj
	 * @return
	 */
	public static String getEntityNameLow(Object obj) {
		return fristCharToLower(getEntityName(obj));
	}

	/**
	 * 根据class获取类名称（不包括包名，首字母小写）
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getEntityNameLow(Class<?> clazz) {
		return fristCharToLower(getEntityName(clazz));
	}

	/**
	 * 根据实例获取类名称（不包括包名）
	 * 
	 * @param obj
	 * @return
	 */
	public static String getEntityName(Object obj) {
		return obj.getClass().getName()
				.replace(obj.getClass().getPackage().getName() + ".", "");
	}

	/**
	 * 根据Class获取类名称（不包括包名）
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getEntityName(Class<?> clazz) {
		return clazz.getName().replace(clazz.getPackage().getName() + ".", "");
	}

	/**
	 * 字符串首字母小写
	 * 
	 * @param str
	 * @return
	 */
	public static String fristCharToLower(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}

	/**
	 * 使用反射执行实例方法
	 * 
	 * @param obj
	 *            实例
	 * @param methodName
	 *            �?��用的方法�?
	 * @param methodParam
	 *            方法参数，可�?null
	 * @param paramValues
	 *            参数值，可为 null
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("rawtypes")
	public static Object invokeMethod(Object obj, String methodName,
			Class[] methodParam, Object[] paramValues) throws Exception {
		Class clazz = obj.getClass();
		@SuppressWarnings("unchecked")
		Method method = clazz.getDeclaredMethod(methodName, methodParam);
		return method.invoke(obj, paramValues);
	}

	/**
	 * 使用反射执行类静态方�?
	 * 
	 * @param clazz
	 *            �?
	 * @param methodName
	 *            �?��用的方法�?
	 * @param methodParam
	 *            方法参数，可�?null
	 * @param paramValues
	 *            参数值，可为 null
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static Object invokeMethod(Class clazz, String methodName,
			Class[] methodParam, Object[] paramValues) throws Exception {
		@SuppressWarnings("unchecked")
		Method method = clazz.getDeclaredMethod(methodName, methodParam);
		return method.invoke(clazz, paramValues);
	}

	/**
	 * 对url进行编码转换
	 * 
	 * @param pathSegment
	 * @param httpServletRequest
	 * @return
	 */
	public static String encodeUrlPathSegment(String pathSegment,
			HttpServletRequest httpServletRequest) throws Exception {
		String enc = httpServletRequest.getCharacterEncoding();
		if (enc == null) {
			enc = "UTF-8";
		}
		pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
		return pathSegment;
	}

	/**
	 * 获取流程配置文件路径
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String getWebInfoDirPath(String path) throws Exception {
		String fileSeparator = "/";// 路径分隔�?Linux �?Windonws 下不�?��) �?��从系统参数中读取

		String physicsSavePath = URLDecoder.decode(new File(
				InvokeAndEncodeHelper.class.getResource(fileSeparator)
						.getPath()).getParentFile().getPath()
				+ File.separator, "UTF-8");

		if (path != null && !"".equals(path)) {
			physicsSavePath += path;
			physicsSavePath += File.separator;
		}

		File file = new File(physicsSavePath);
		if (!file.exists()) {
			file.mkdir();
		}
		return physicsSavePath;
	}

	/**
	 * 获取整个系统存放的目�?
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getSystemDirPath() throws Exception {
		String fileSeparator = "/";// 路径分隔�?Linux �?Windonws 下不�?��) �?��从系统参数中读取

		String physicsSavePath = URLDecoder.decode(new File(
				InvokeAndEncodeHelper.class.getResource(fileSeparator)
						.getPath()).getParentFile().getParentFile().getPath()
				+ File.separator, "UTF-8");
		return physicsSavePath;
	}

	/**
	 * 反射判断指定类中是否存在指定属�?
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByName(Class<?> clazz, String fieldName) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
		} catch (Exception e) {
		}
		return field;
	}

	/**
	 * 反射判断指定类中是否存在指定属�?,包括父类中的属�?
	 * 
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByNameSuper(Class<?> clazz, String fieldName) {
		Field field = null;
		try {

			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
		} catch (Exception e) {
			if (clazz.getSuperclass() != null
					&& clazz.getSuperclass() != Object.class) {
				field = getFieldByName(clazz.getSuperclass(), fieldName);
			}
		}
		return field;
	}

	/**
	 * 获取指定类的�?��属�?
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<String> getFields(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		if (fields != null) {
			for (Field field : fields) {
				list.add(field.getName());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Title: getFields
	 * @Description: 获取指定类的�?��属�?,包括父类
	 * @param @param clazz
	 * @param @param list
	 * @param @return 设定文件
	 * @return List<String> 返回类型
	 * @throws
	 */
	public static List<String> getFields(Class<?> clazz, List<String> list) {
		Field[] fields = clazz.getDeclaredFields();
		if (list == null) {
			list = new ArrayList<String>();
		}
		if (fields != null) {
			for (Field field : fields) {
				list.add(field.getName());
			}
		}
		if (clazz.getSuperclass() != null
				&& clazz.getSuperclass() != Object.class) {
			getFields(clazz.getSuperclass(), list);
		}
		return list;
	}

}

