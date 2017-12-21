package edu.swust.goods.utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
/**
 * 负责图片保存和删除
 * @author hanpeng
 *
 */
public class ImageUtil {
    private static final String[] imgDirs = {"http://localhost:8080/unusedgoods/upload/headImgs",
    		                                "http://localhost:8080/unusedgoods/upload/goodsImgs"};
    private static final String HEAD_DIR = "http://localhost:8080/unusedgoods";
    private static final String[] relativelyPaths = {"/upload/headImgs", "/upload/goodsImgs"};
    private static final String[] IMG_TYPE = {"jpg","png","gif","bmp"};
    public static String saveImage(File image, String imageName, int postion) {
    	String ext = FilenameUtils.getExtension(imageName);
		System.out.println(ext);
		String result = null;
		if(Arrays.asList(IMG_TYPE).contains(ext)) {
			try {
				StringBuffer buffer = new StringBuffer(imgDirs[postion]);
		        String filePath = ServletActionContext.getServletContext().getRealPath(relativelyPaths[postion]);
		        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + imageName;
		        FileUtils.copyFile(image, new File(filePath, fileName));
		        buffer.append("/");
		        buffer.append(fileName);
		        result = buffer.toString();
		        System.out.println(buffer.toString());
			} catch (IOException e) {
				
			}
		}
    	
    	return result;
	}
    
    public static String save(File image, String imageName) {

		String result = null;
		
		try {
			StringBuffer buffer = new StringBuffer(imgDirs[0]);
	        String filePath = ServletActionContext.getServletContext().getRealPath(relativelyPaths[0]);
	        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + imageName;
	        FileUtils.copyFile(image, new File(filePath, fileName));
	        buffer.append("/");
	        buffer.append(fileName);
	        result = buffer.toString();
	        System.out.println(result);
		} catch (IOException e) {
			
		}
    	return result;
	}
    
    public static boolean deleteImage(String url) {
		url = url.replaceAll(HEAD_DIR, "");
		String filePath = ServletActionContext.getServletContext().getRealPath(url);
	    System.out.println(filePath);
	    File file = new File(filePath);
	    System.out.println(file.length());
		return file.delete();
	}
}
