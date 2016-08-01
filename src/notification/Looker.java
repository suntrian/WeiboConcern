package notification;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import notification.mail.MailSender;
import storage.Common;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.util.WeiboConfig;
import weibostatic.Context;

public class Looker extends Thread{
	
	private StatusWapper statusWapper;
	private String[] ids;
	private String[] keywords;
	private Context context;
	int mode = 0;
	
	public Looker(Context context,StatusWapper statusWapper, String[] ids, String[] keywords) {
		super();
		this.context = context;
		this.statusWapper = statusWapper;
		this.ids = ids;
		this.keywords = keywords;
		mode  =mode();
	}
	
	
	private void lookIntoWrapper() throws SQLException{
		
		long latestStatus = Context.dbStorage.queryLatestStatusByDate();
		
		for(Status status: statusWapper.getStatuses()){
			String statusID  =status.getId();
			String userID = status.getUser().getId();
			String userName = status.getUser().getName();
			String statusText = status.getText();
			long statusCreatAt = status.getCreatedAt().getTime();
			if (statusCreatAt <= latestStatus){
				continue;
			}	
			if(matchStringById(ids, userID, userName) ){
				//do send e-mail		
				dealWithStatus(status);
			}else if (matchStringByKey(keywords, statusText)) {
				dealWithStatus(status);
			}
		}
		
	}
	
	
	private void dealWithStatus(Status status){
		try {
			Context.info("Concern Found!");
			if(Context.sendMail)
				Context.mailSender.send(WeiboConfig.getValue("mail_recipient"), status.getUser().getName(), status.getText());
			
			for(String url: status.getLargePics()){
				try {
					String picName = Common.getFileNameFromPath(url);
					String picPath = Context.localStorage.download(url);
					if(Context.uploadNetdisk){
						Context.cyberStorage.upload(picPath);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			}
			Context.dbStorage.insertStatus(status.getId(), status.getUser().getId(), String.valueOf(status.getCreatedAt().getTime()), status.getText(), String.join(",", status.getLargePics()));
			Context.info("Weibo Concern found at " + new Date().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			lookIntoWrapper();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int mode(){
		if(ids==null){
			return 0;
		}else if (keywords==null){
			return 1;
		}
		return 2;
	}
	
	/**
	 * 
	 * @param strings
	 * @param string
	 * @param matchpattern 0:full text match, 1:contains
	 * @return s
	 */
	private boolean matchStringById(String[] strings,  String id, String name){
		if( strings == null) return false;
		String string = id;
		for (String str:strings){
			if(Common.judgeString(str) != 0){
				string = name;
			}
			if(str.equals(string)){
				return true;
			}
		}
		return false;
	}
	
	private boolean matchStringByKey(String[] strings, String text){
		if(strings == null) return false;
		for (String str:strings){
			if(text.contains(str)){
				return true;
			}
		}
		return false;
	}
	
	
	
}
