package weibostatic;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import notification.Looper;
import notification.mail.MailSender;
import storage.Common;
import storage.DbStorage;
import storage.LocalStorage;
import storage.QiniuStorage;
import timerjob.UpdateTask;
import weibo4j.util.WeiboConfig;

public class Context {
	public static weibo4j.Timeline timeline;
	private static Token token ;
	public static MailSender mailSender;
	public static Logger logger;
	public static DbStorage dbStorage;
	public static LocalStorage localStorage;
	public static QiniuStorage cyberStorage;
	
	public static final String DATABASE = "weiboconcern.db";
	public static final String TABLE_STATUS = "status";
	public static final String RUN_DIR = "www";
	public static final String PICTURE_DIR = "pictures";
	
	public static boolean sendMail = true;
	public static boolean uploadNetdisk = true;
	public static boolean savePictures = true;
	
	
	public Looper looper;
	public UpdateTask upTask;
	
	public Context(){
		token = new Token();
		timeline = new weibo4j.Timeline(WeiboConfig.getValue("access_token"));
		mailSender = new MailSender(WeiboConfig.getValue("mail_username"), WeiboConfig.getValue("mail_password"));
		dbStorage = new DbStorage(Common.getUserDirectory() + Common.getSystemSeparator() + RUN_DIR + Common.getSystemSeparator() + DATABASE);
		dbStorage.initial_db();
		localStorage = new LocalStorage(Common.getUserDirectory() + Common.getSystemSeparator() + PICTURE_DIR  + Common.getSystemSeparator());
		cyberStorage = new QiniuStorage();
		
		looper = new Looper(this, new String[]{"3861743103","1225409277"});
		upTask = new UpdateTask(this, new Date().toString());
		
		logger = Logger.getLogger("log");
	}
	
	public static void info( String msg){
		logger.log(Level.INFO, msg);
	}
	
	
}
