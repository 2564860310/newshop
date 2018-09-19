package com.cyhz.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	
	private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
	
	private static final SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final Random r=new Random();	 
	
	//压缩图片打个水印
	public static String generateThumbnail(InputStream thumbnailInputStream,String fileName,String targetAddr) {
		String realFileName=getRandomFileName();
		String extension=getFileExtension(fileName);
		makeDirPath(targetAddr);
		String relativeAddr=targetAddr+realFileName+extension;
		File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
		try {
			Thumbnails.of(thumbnailInputStream).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/shuiyin.jpg")), 0.5f)
			.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}
	/**
	 * 创建目标路径所涉及到的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath=PathUtil.getImgBasePath()+targetAddr;
		File dirPath=new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名(jpg,png)
	 * @param fileName
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒+5位随机数
	 * @return
	 */
	public static String getRandomFileName() {
		//获取5位随机数
		int rannum=r.nextInt(89999)+10000;
		String nowTimeStr=sDateFormat.format(new Date());
		return nowTimeStr+rannum;
	}

	/**
	 * storePath是文件路径还是目录路径,
	 * 如果storePath是文件路径就删除该文件,
	 * 如果是storePath是目录路径则删除该目录下所有的文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath){
		File fileOrPath = new File(PathUtil.getImgBasePath()+storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File files[]=fileOrPath.listFiles();
				for (int i = 0; i < files.length ; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}

	/*public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("D:/2017061320315746624.jpg"))
		.size(200, 200).watermark(Positions.BOTTOM_LEFT,ImageIO.read(new File(basePath+"/shuiyin.jpg")),0.5f)
		.outputQuality(0.8f).toFile("D:/shuiyin.jpg");;
		System.out.println(basePath+"/cyhz.png");
	}*/
}
