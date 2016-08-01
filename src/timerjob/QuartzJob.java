package timerjob;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;

import weibostatic.Context;

public class QuartzJob{

	String status;
	String picpath;
	Scheduler sched;
	JobDetail jobDetail;
	CronTrigger trigger;
	
	
	public QuartzJob(JobDetail job,Trigger trigger) {
		// TODO Auto-generated constructor stub
		try {
			SchedulerFactory schedFactory = new StdSchedulerFactory();
			this.sched = schedFactory.getScheduler();
			this.jobDetail = job;
			this.trigger = (CronTrigger)trigger;
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		try {
			Date dd = sched.scheduleJob(jobDetail, trigger);
			sched.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void pauseAllSchedule(){
		try {
			sched.pauseAll();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void resumeAllSchedule(){
		try {
			sched.resumeAll();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopSchedule(){
		try {
			sched.shutdown(true);
			int num = sched.getMetaData().getNumberOfJobsExecuted();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}
	public void deleteJob(JobDetail job){
		try {
			sched.deleteJob(job.getKey());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Scheduler getSched() {
		return sched;
	}

	public void setSched(Scheduler sched) {
		this.sched = sched;
	}
}
