package timerjob;

import org.quartz.SchedulerException;

import weibostatic.Context;

public class UpdateTask extends Thread{

	Context global;
	String status;
	String picpath;
	
	QuartzJob quartzJob;
	UpdateJob updateJob;
	
	public UpdateTask(Context global, String status) {
		// TODO Auto-generated constructor stub
		this.global = global;
		this.status = status;
	
		updateJob = new UpdateJob(global, status);
		quartzJob = new QuartzJob(updateJob.getJob(), updateJob.getTrigger());
	}
	
	public UpdateTask(Context global, String status, String picpath) throws SchedulerException {
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
	
	public void stopUpdate(){
		this.quartzJob.stopSchedule();
	}
	
}
