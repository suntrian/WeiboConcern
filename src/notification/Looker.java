package notification;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import notification.mail.MailSender;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.util.WeiboConfig;

public class Looker extends Thread{
	
	private StatusWapper statusWapper;
	private String[] ids;
	private String[] keywords;
	public Looker(StatusWapper statusWapper, String[] ids, String[] keywords) {
		super();
		this.statusWapper = statusWapper;
		this.ids = ids;
		this.keywords = keywords;
	}
	
	
	private void lookIntoWrapper(){
		
		for(Status status: statusWapper.getStatuses()){
			String userID = status.getUser().getId();
			String userNick = status.getUser().getName();
			String statusText = status.getText();
			String picUrl = status.getOriginalPic();
			try {
				
				
				if(matchString(ids, userID, userNick, 0 ) ){
					//do send e-mail
					MailSender sender = new MailSender(WeiboConfig.getValue("mail_username"), WeiboConfig.getValue("mail_password"));
					Logger.getLogger("liil")
					.log(Level.FINE, WeiboConfig.getValue("mail_username") + "  " + WeiboConfig.getValue("mail_password"));
					sender.send(WeiboConfig.getValue("mail_recipient"), userNick, statusText);
					Logger.getAnonymousLogger().log(Level.FINE, "mail sent");
				}

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		lookIntoWrapper();
		
	}
	
	/**
	 * 
	 * @param strings
	 * @param string
	 * @param matchpattern 0:full text match, 1:contains
	 * @return s
	 */
	private boolean matchString(String[] strings,  String id, String name, int matchpattern){
		if( strings == null) return false;
		String string = id;
		for (String str:strings){
			if(judgeString(str) != 0){
				string = name;
			}
			switch (matchpattern) {
			case 0:
				if (str.equals(string) ) {
					return true;
				}
				break;
			case 1:
			default:
				if( string.contains(str)){
					return true;
				}
				break;
			}
		}
		return false;
	}
	
	/**
	 * judge a String is weiboid or weiboname
	 * @return int  0: id ,1:name
	 */
	
	public int judgeString(String str){
		try{
			Long.valueOf(str);
			return 0;
		}catch (NumberFormatException e) {
			// TODO: handle exception
		}
		return 1;
	}
	
	
	
}
