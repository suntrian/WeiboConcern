package weibostatic;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class Token {
	
	private Oauth oauth;
	private String access_token ;
	
	protected String getAccess_token() {
		return access_token;
	}

	protected void setAccess_token(String access_token) {
		this.access_token = access_token;
		WeiboConfig.updateProperties("access_token", access_token);
	}

	protected Token() {
		// TODO Auto-generated constructor stub
		oauth = new Oauth();
		
	}
	
	public String requestCode(){
		try {
			//获取code的url_api
			String oauthUrl =  oauth.authorize("code");
			//打开url，code通过回调函数返回给回调服务器
			
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public AccessToken updateAccessToken(String code){
		AccessToken token;
		try {
			token = oauth.getAccessTokenByCode(code);
			setAccess_token(token.getAccessToken());
			return token;
		} catch (WeiboException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
