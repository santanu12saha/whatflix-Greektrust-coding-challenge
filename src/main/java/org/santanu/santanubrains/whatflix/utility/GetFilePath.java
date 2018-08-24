package org.santanu.santanubrains.whatflix.utility;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.inject.Singleton;

@Singleton
public class GetFilePath {

	public String getPath(String fileName) {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = "";
		try {
			fullPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String reponsePath = "";
		reponsePath = new File(fullPath).getPath() + File.separatorChar + fileName;
		return reponsePath;
	}
}
