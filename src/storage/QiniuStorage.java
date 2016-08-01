package storage;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import weibo4j.util.WeiboConfig;


public class QiniuStorage {
	String accessKey;
	String secretKey;
	Auth auth;
	UploadManager upManager;
	String bucket = "weiboconcern";
	static String DOMAIN = "http://7xwwgj.com2.z0.glb.qiniucdn.com/";
	
	public QiniuStorage() {
		// TODO Auto-generated constructor stub
		accessKey = WeiboConfig.getValue("storage_accesskey");
		secretKey = WeiboConfig.getValue("storage_secretkey");
		 auth = Auth.create(accessKey, secretKey);
		 upManager = new UploadManager();
	}
	public String getUpToken(){
		return auth.uploadToken(bucket);
	}
	
	public void upload(String filepath){
		try {
			Response response = upManager.put(filepath, Common.getFileNameFromPath(filepath), getUpToken());
			System.out.println(response.toString());
		} catch (QiniuException e) {
			// TODO: handle exception
			Response r = e.response;
			System.out.println(r.toString());
			try{
				System.out.println(r.bodyString());
			}catch (QiniuException el) {
				// TODO: handle exception
				// do nothing?
			}
		}
		
	}

	public UploadManager getUpManager() {
		return upManager;
	}
	public void setUpManager(UploadManager upManager) {
		this.upManager = upManager;
	}

	
	public static void main(String[] args){
		String upFilePath = "C:\\Users\\suntr\\Pictures\\Saved Pictures\\test1.png";
		//can't work  upFilePath = "http://pic64.nipic.com/file/20150417/8952533_134341461000_2.jpg";
		new QiniuStorage().upload(upFilePath);
	}
}
