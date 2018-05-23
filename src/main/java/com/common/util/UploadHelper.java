package com.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class UploadHelper {
    
    private static int UPLOAD_COUNT = 0;
    
    /**
     * 有大小两张缩略图
     * @param request
     * @param filedata
     * @param userType
     * @return
     */
    public static String uploadFile(HttpServletRequest request, String filedata, String userType) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multipartRequest.getFile(filedata);
        String fileName = "";
        String tempPath = "";
        String result = "";
        if (multFile != null) {
            fileName = multFile.getOriginalFilename();// 取得上传的文件名
            if (("").equals(fileName) || fileName == null) {
                return "";
            }
            String folderName = createName(fileName);
            if (("").equals(folderName) || folderName == null) {
                return "";
            }
            // 扩展名格式：
            File f1 = null;
            File newFile = null;
            String childFolder = getDate(new Date(), "yyyy");
            String childChildFolder = getDate(new Date(), "MMdd");
            try {
                tempPath = getAttachSavePath();
                String newFilePath = tempPath + userType + File.separator + childFolder
                        + File.separator + childChildFolder + File.separator;
                String shortTempPath1 = tempPath + File.separator + "short" + File.separator + userType
                        + File.separator + childFolder + File.separator + childChildFolder + File.separator;
                String shortTempPath2 = tempPath + File.separator + "bigShort" + File.separator + userType
                        + File.separator + childFolder + File.separator + childChildFolder + File.separator;
                // 原图片路径
                f1 = new File(newFilePath);
                if (!f1.exists()) {
                    f1.setWritable(true);
                    f1.mkdirs();// 建立目录
                }
                File shortFile1 = new File(shortTempPath1);
                if (!shortFile1.exists()) {
                    shortFile1.setWritable(true);
                    shortFile1.mkdirs();// 建立目录
                }
                File shortFile2 = new File(shortTempPath2);
                if (!shortFile2.exists()) {
                    shortFile2.setWritable(true);
                    shortFile2.mkdirs();// 建立目录
                }

                // 生成缩略图
                scale(multFile, shortTempPath2, folderName, 400);
                scale(multFile, shortTempPath1, folderName, 200);
                // 生成原图
                newFile = new File(newFilePath + folderName);
                multFile.transferTo(newFile);
                // 返回链接地址
                result = userType + File.separator + childFolder + File.separator + childChildFolder
                        + File.separator + folderName;
                return FileAddress.UPLOAD_URL + result;
            } catch (Exception e) {
                // 删除已上传的文件
                newFile.delete();
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }
    
    /**
     * 无缩略图
     * @param request
     * @param filedata
     * @param userType
     * @return
     */
    public static String uploadFileNoShort(HttpServletRequest request, String filedata, String userType) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multFile = multipartRequest.getFile(filedata);
        String fileName = "";
        String tempPath = "";
        String result = "";
        if (multFile != null) {
            fileName = multFile.getOriginalFilename();// 取得上传的文件名
            if (("").equals(fileName) || fileName == null) {
                return "";
            }
            String folderName = createName(fileName);
            if (("").equals(folderName) || folderName == null) {
                return "";
            }
            // 扩展名格式：
            File f1 = null;
            File newFile = null;
            String childFolder = getDate(new Date(), "yyyy");
            String childChildFolder = getDate(new Date(), "MMdd");
            try {
                tempPath = getAttachSavePath();
                String newFilePath = tempPath + userType + File.separator + childFolder + File.separator
                        + childChildFolder + File.separator;
                // 原图片路径
                f1 = new File(newFilePath);
                if (!f1.exists()) {
                    f1.setWritable(true);
                    f1.mkdirs();// 建立目录
                }
                // 生成原图
                newFile = new File(newFilePath + folderName);
                multFile.transferTo(newFile);
                // 返回链接地址
                result = userType + File.separator + childFolder + File.separator + childChildFolder
                        + File.separator + folderName;
                return FileAddress.UPLOAD_URL + result;
            } catch (Exception e) {
                // 删除已上传的文件
                newFile.delete();
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 多图上传
     * @param multFile
     * @param userType
     * @return
     */
    public static List<String> uploadManyFiles(HttpServletRequest request, String userType){
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
    			request.getSession().getServletContext());
    	// 判断 request 是否有文件上传,即多部分请求
    	List<String> list = new ArrayList<String>();
    	if (multipartResolver.isMultipart(request)) {
    		// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			String newFileName = null;
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					newFileName = UploadHelper.multipartUpload(file, userType);
					list.add(newFileName);
				}
			}
    	}
    	return list;
    }
    
    /**
     * 多图上传 辅助
     * @param multFile
     * @param userType
     * @return
     */
    private static String multipartUpload(MultipartFile multFile, String userType){
    	String fileName = "";
        String tempPath = "";
        String result = "";
        if (multFile != null) {
            fileName = multFile.getOriginalFilename();// 取得上传的文件名
            if (("").equals(fileName) || fileName == null) {
                return "";
            }
            String folderName = createName(fileName);
            if (("").equals(folderName) || folderName == null) {
                return "";
            }
            // 扩展名格式：
            File f1 = null;
            File newFile = null;
            String childFolder = getDate(new Date(), "yyyy");
            String childChildFolder = getDate(new Date(), "MMdd");
            try {
                tempPath = getAttachSavePath();
                String newFilePath = tempPath + userType + File.separator + childFolder + File.separator
                        + childChildFolder + File.separator;
                String shortTempPath = tempPath + File.separator + "short" + File.separator + userType
                        + File.separator + childFolder + File.separator + childChildFolder + File.separator;
                // 原图片路径
                f1 = new File(newFilePath);
                if (!f1.exists()) {
                    f1.setWritable(true);
                    f1.mkdirs();// 建立目录
                }
                // 生成缩略图
                scale(multFile, shortTempPath, folderName, 400);
                // 生成原图
                newFile = new File(newFilePath + folderName);
                multFile.transferTo(newFile);
                // 返回链接地址
                result = userType + File.separator + childFolder + File.separator + childChildFolder
                        + File.separator + folderName;
                return result;
            } catch (Exception e) {
                // 删除已上传的文件
                newFile.delete();
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }
    
    
    public static String createName(String imgName) {
    	String ranNum = String.valueOf(1 + (int) (Math.random() * 1000));
    	if (imgName.toLowerCase().contains(".jpg")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".JPEG";
    	} else if (imgName.toLowerCase().contains(".bmp")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".BMP";
    	} else if (imgName.toLowerCase().contains(".gif")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".GIF";
    	} else if (imgName.toLowerCase().contains(".png")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".PNG";
    	} else if (imgName.toLowerCase().contains(".jpeg")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".JPEG";
    	} else if (imgName.toLowerCase().contains(".swf")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".SWF";
    	} else if (imgName.toLowerCase().contains(".flv")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".FLV";
    	} else if (imgName.toLowerCase().contains(".mp3")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".MP3";
    	} else if (imgName.toLowerCase().contains(".wav")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".WAV";
    	} else if (imgName.toLowerCase().contains(".wma")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".WMA";
    	} else if (imgName.toLowerCase().contains(".wmv")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".WMV";
    	} else if (imgName.toLowerCase().contains(".mid")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".MID";
    	} else if (imgName.toLowerCase().contains(".avi")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".AVI";
    	} else if (imgName.toLowerCase().contains(".mpg")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".MPG";
    	} else if (imgName.toLowerCase().contains(".asf")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".ASF";
    	} else if (imgName.toLowerCase().contains(".rm")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".RM";
    	} else if (imgName.toLowerCase().contains(".rmvb")) {
    		return getDate(new Date(), "yyyyMMddHHmmss") + ranNum + UPLOAD_COUNT + ".rmvb";
    	}
    	return "";
    }
    
    /**
     * 生成缩略图
     * 
     * @param srcImageFile
     * @param result
     * @throws Exception
     */
    private static void scale(MultipartFile myfile, String shortTempPath, String imgName, int shortHeight)
            throws Exception {
        try {
            File shortMagFile = new File(shortTempPath);
            if (!shortMagFile.exists()) {
                shortMagFile.setWritable(true);
                shortMagFile.mkdirs();// 建立目录
            }
            // JPEG / BMP/png
            ByteArrayInputStream bais = new ByteArrayInputStream(myfile.getBytes());
            MemoryCacheImageInputStream mciis = new MemoryCacheImageInputStream(bais);
            Image src = ImageIO.read(mciis);
            // BufferedImage src = ImageIO.read(myfile); // 读入文件
            int width = src.getWidth(null); // 得到源图宽
            int height = src.getHeight(null); // 得到源图长
            int shortWidth = 0;
            //比例参数
            float scale = (float) height / (float) shortHeight;
            shortWidth = Math.round((float) width / scale);
            // 按比例缩小
            width = shortWidth;
            height = shortHeight;
            BufferedImage tag = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_3BYTE_BGR);
            tag.getGraphics().drawImage(src, 0, 0, (int) width, (int) height, null); // 绘制缩小后的图
            shortTempPath += imgName;
            if (imgName.toLowerCase().contains(".jpg") || imgName.toLowerCase().contains(".jpeg")) {
                ImageIO.write(tag, "JPEG", new File(shortTempPath));// 输出到文件流
            } else if (imgName.toLowerCase().contains(".bmp")) {
                ImageIO.write(tag, "BMP", new File(shortTempPath));// 输出到文件流
            } else if (imgName.toLowerCase().contains(".gif")) {
                ImageIO.write(tag, "GIF", new File(shortTempPath));// 输出到文件流
            } else if (imgName.toLowerCase().contains(".png")) {
                ImageIO.write(tag, "png", new File(shortTempPath));// 输出到文件流
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     * 
     * @param sPath
     *            要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     * @throws Exception
     */
    @SuppressWarnings("static-access")
	public boolean DeleteFolder(String deviceNumber) throws Exception {
        String tempPath = this.getAttachSavePath();
        String sPath = tempPath + "user" + File.separator;
        boolean flag = false;
        File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
    
    /**
     * 删除单个文件
     * 
     * @param sPath
     *            被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
    
    /**
     * 删除目录（文件夹）以及目录下的文件
     * 
     * @param sPath
     *            被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     * @throws Exception
     */
    public boolean deleteDirectory(String sPath) throws Exception {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    
    /***
     * 获取Attachment的路径
     * 
     * @return
     * @throws Exception
     */
    public static String getAttachSavePath() throws Exception {
        //return InvokeAndEncodeHelper.getWebInfoDirPath("userdata");
    	return InvokeAndEncodeHelper.getSystemDirPath();
    }
    
    private static String getDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdFormat = new SimpleDateFormat(format);
        return sdFormat.format(date);
    }
    
}

