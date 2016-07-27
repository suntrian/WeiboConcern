package storage;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class Upload {
	
	String ACCESS_KEY = "key";
	String SECRET_KEY = "secret";
	String bucketName = "Bucket_Name";		//要上传的空间名称
	String fileName = "filename.jpg";		//上传后的文件名
	String filePath = "/../../.../*.jpg";	
	
	Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	UploadManager upManager = new UploadManager();

	public String getUpToken(){
		return auth.uploadToken(bucketName);
	}
	public void upload() throws IOException{
		try{
			Response res  = upManager.put(filePath, fileName, getUpToken());
			System.out.println(res.bodyString());
		}catch (QiniuException e) {
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
	
	
}
