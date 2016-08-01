package weibostatic;

import weibo4j.util.WeiboConfig;

public class WeiboGlobal {
	public static weibo4j.Timeline timeline;
	private static Token token ;
	public WeiboGlobal(){
		token = new Token();
		timeline = new weibo4j.Timeline(WeiboConfig.getValue("access_token"));
	}
	
}
