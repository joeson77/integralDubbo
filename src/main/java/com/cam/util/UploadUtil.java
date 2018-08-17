package com.cam.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午9:28:38
 *
 * 功能描述： 上传工具
 * 
 * 版本： V1.0
 */
public class UploadUtil {

	public static String upload(MultipartFile file, String basePath) {
		String orgFileName = file.getOriginalFilename();
		String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(orgFileName);
		try {
			FileUtils.writeByteArrayToFile(new File(basePath, fileName), file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
