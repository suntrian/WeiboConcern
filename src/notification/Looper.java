package notification;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import weibo4j.Timeline;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;
import weibostatic.WeiboGlobal;

public class Looper extends Thread{
	
	WeiboGlobal global;
	String[] ids;
	String[] keywords;
	boolean flag = false;
	
	public Looper(WeiboGlobal global, String[] ids) {
		this.global = global;
		this.ids = ids;
		this.keywords = null;
	}
	
	
	public Looper(WeiboGlobal global, String[] ids,String[] keywords) {
		// TODO Auto-generated constructor stub
		this.global = global;
		this.ids = ids;
		this.keywords = keywords;
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		while (!flag) {
			try {
				StatusWapper statusWapper = global.timeline.getFriendsTimeline();
				Looker looker = new Looker(statusWapper, this.ids, this.keywords);
				looker.start();
				
				Thread.sleep(60000);
				
				
			} catch (WeiboException e) {
				Logger.getAnonymousLogger().log(Level.WARNING, e.getError());
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public  void stoplooper(){
		this.flag = true;
	}
	
	
}

