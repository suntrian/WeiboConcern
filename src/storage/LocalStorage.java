package storage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class LocalStorage {
	private String saveDir = null;
	private String defaultSaveDir = "pictures";
	static String savePath;
	
	public LocalStorage() {
		// TODO Auto-generated constructor stub
	}
	
	public LocalStorage(String saveDir){
		this.saveDir = saveDir;
		
	}
	
	public String download(String urlString){
		String fileName = Common.getFileNameFromPath(urlString);

		if(saveDir==null){
			saveDir =  Common.USER_DIRECTORY + Common.FILE_SEPARATOR + defaultSaveDir 
					+ Common.FILE_SEPARATOR ;
			savePath = saveDir + fileName;
		}else {
			savePath  = saveDir + fileName;
		}
		URLConnection connection;
		try {
			URL url = new URL(urlString);
			connection = url.openConnection();
			connection.setConnectTimeout(30*1000);
			InputStream is = connection.getInputStream();
			
			byte[] fileBytes = readInputStreamAsByteArray(is);
			File file = new File(savePath);
			FileOutputStream fou = new FileOutputStream(file);
			fou.write(fileBytes);
			fou.close();
			return savePath;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] readInputStreamAsByteArray(InputStream is) throws IOException {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len  = 0;
		while ((len=is.read(buffer)) != -1){
			outputStream.write(buffer,0,len);
		}
		is.close();
		return outputStream.toByteArray();
	}
	
	public static void main(String[] args) {
		//download("http://img3.duitang.com/uploads/item/201501/20/20150120122858_42YsY.png");
	}
}
