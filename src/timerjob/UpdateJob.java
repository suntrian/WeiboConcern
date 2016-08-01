package timerjob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import weibostatic.Context;

public class UpdateJob implements Job{

	Context global ;
	String statu;
	String picpath;
	JobDetail job;	
	CronTrigger trigger;


	//String cronPattern = "56 59 23/6 * * ?";
	String cronPattern = "0 */5 * * * ?";
	public UpdateJob(Context global, String statu, String picpath) {
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

	public UpdateJob(Context global, String statu) {
		super();
		this.global = global;
		this.statu = statu;
		this.picpath = null;
		job = newJob(UpdateJob.class).withIdentity("update job","Weibo")
				.build();
		trigger = newTrigger().withIdentity("update trigger", "Weibo")
				.withSchedule(CronScheduleBuilder.cronSchedule(cronPattern))
				.build();
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		UpdateWb up = new UpdateWb(global);
		if(picpath!=null){
			up.update(statu, picpath);
		}else{
			up.update(statu);
		}
		Context.info("Update Weibo at" + new Date().toString());
		
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
