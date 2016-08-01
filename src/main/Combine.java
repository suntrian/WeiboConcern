package main;

import org.quartz.SchedulerException;

import notification.Command;
import notification.Looper;
import storage.Common;
import timerjob.QuartzJob;
import timerjob.UpdateJob;
import timerjob.UpdateTask;
import weibostatic.Context;

public class Combine {
	
	Context global;
	UpdateTask upTask;
	Looper concernLooper;
	
	public Combine() {
		// TODO Auto-generated constructor stub
		try {
			global = new Context();
		
			upTask = new UpdateTask(global, "TEST5 TEST5 TEST5 TEST5", "C:\\Users\\suntr\\Pictures\\Saved Pictures\\test1.png");
		
			//upTask.start();
			
			concernLooper = new Looper(global, new String[]{"3861743103"});
			concernLooper.start();
			
			Command cmd = new Command();
		
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
