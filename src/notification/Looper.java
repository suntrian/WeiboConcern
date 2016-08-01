package notification;

import java.util.logging.Level;
import java.util.logging.Logger;

import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibostatic.Context;

public class Looper extends Thread{
	
	Context global;

	String[] ids;
	String[] keywords;
	final int  INTERVAL = 5*60*1000;
	static boolean flag = false;
	
	public Looper(Context global, String[] ids) {
		this.global = global;
		this.ids = ids;
		this.keywords = null;
	}
	
	
	public Looper(Context global, String[] ids,String[] keywords) {
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
				Looker looker = new Looker(global,statusWapper, this.ids, this.keywords);
				looker.start();
				
				Thread.sleep(INTERVAL);
				
				
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
	
	public static void stoplooper(){
		flag = true;
	}
	
	public String[] getIds() {
		return ids;
	}


	public void setIds(String[] ids) {
		this.ids = ids;
	}


	public String[] getKeywords() {
		return keywords;
	}


	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

}

