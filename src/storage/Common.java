package storage;

import java.io.File;

public class Common {
	public static String USER_DIRECTORY = getUserDirectory();
	public static String FILE_SEPARATOR = getSystemSeparator();
	
	public static String getFileNameFromPath(String filePath){
		File tempFile = new File(filePath);
		return tempFile.getName();
	}
	
	public static String getUserDirectory(){
		if(USER_DIRECTORY == null)
			USER_DIRECTORY = System.getProperty("user.home");
		return USER_DIRECTORY;
	}
	
	public static String getSystemSeparator(){
		if(FILE_SEPARATOR == null){
			FILE_SEPARATOR = System.getProperty("file.separator");
		}
		return FILE_SEPARATOR;
	}
	
	/**
	 * judge a String is weiboid or weiboname
	 * @return int  0: id ,1:name
	 */
	
	public static int judgeString(String str){
		try{
			Long.valueOf(str);
			return 0;
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return 1;
	}
}
