package timerjob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import weibostatic.WeiboGlobal;

public class UpdateJob implements Job{

	WeiboGlobal global ;
	String statu;
	String picpath;
	JobDetail job;	
	CronTrigger trigger;


	String cronPattern = "56 59 23/6 * * ?";

	public UpdateJob(WeiboGlobal global, String statu, String picpath) {
		super();
		this.global = global;
		this.statu = statu;
		this.picpath = picpath;
		job = newJob(UpdateJob.class).withIdentity("update job","Weibo")
				.build();
		trigger = newTrigger().withIdentity("update trigger", "Weibo")
				.withSchedule(CronScheduleBuilder.cronSchedule(cronPattern))
				.build();
	}

	public UpdateJob(WeiboGlobal global, String statu) {
		super();
		this.global = global;
		this.statu = statu;
		job = newJob(UpdateJob.class).withIdentity("update","Weibo")
				.build();
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		new UpdateWb(global).update(statu, picpath);
	}

	
	public JobDetail getJob() {
		return job;
	}

	public void setJob(JobDetail job) {
		this.job = job;
	}
	public CronTrigger getTrigger() {
		return trigger;
	}

	public void setTrigger(CronTrigger trigger) {
		this.trigger = trigger;
	}
	
}
