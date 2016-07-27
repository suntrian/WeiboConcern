package timerjob;

import org.quartz.SchedulerException;

import weibostatic.WeiboGlobal;

public class UpdateTask extends Thread{

	WeiboGlobal global;
	String status;
	String picpath;
	
	QuartzJob quartzJob;
	UpdateJob updateJob;
	
	public UpdateTask(WeiboGlobal global, String status, String picpath) throws SchedulerException {
		// TODO Auto-generated constructor stub
		this.global = global;
		this.status = status;
		this.picpath = picpath;
		
		updateJob = new UpdateJob(global, status, picpath);
		quartzJob = new QuartzJob(updateJob.getJob(), updateJob.getTrigger());
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		this.quartzJob.run();
	
	}
	
}
