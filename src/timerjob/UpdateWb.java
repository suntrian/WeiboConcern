package timerjob;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;
import weibostatic.WeiboGlobal;

public class UpdateWb {
	
	WeiboGlobal global;
	
	public UpdateWb(final WeiboGlobal global) {
		// TODO Auto-generated constructor stub
		this.global = global;
	}
	
	public String update(String text){
		try {
			Status status = global.timeline.updateStatus(text);
			return status.getText();
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String update(String text, String picpath){
		try {
			Status status = global.timeline.uploadStatus(text, readFileImage(picpath));
			return status.getText();
		} catch (WeiboException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ImageItem readFileImage(String filename) throws IOException, WeiboException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(filename));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return new ImageItem("pic",bytes);
		
	}
}
