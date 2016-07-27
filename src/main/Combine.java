package main;

import org.quartz.SchedulerException;

import notification.Looper;
import timerjob.QuartzJob;
import timerjob.UpdateJob;
import timerjob.UpdateTask;
import weibostatic.WeiboGlobal;

public class Combine {
	
	WeiboGlobal global;
	UpdateTask upTask;
	Looper concernLooper;
	
	public Combine() {
		// TODO Auto-generated constructor stub
		try {
			global = new WeiboGlobal();
		
			upTask = new UpdateTask(global, "TEST5 TEST5 TEST5 TEST5", "C:\\Users\\suntr\\Pictures\\Saved Pictures\\test.png");
		
			upTask.start();
			
			concernLooper = new Looper(global, new String[]{"3861743103"});
			concernLooper.start();
		
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		upTask.start();
		concernLooper.start();
	}
	
}
