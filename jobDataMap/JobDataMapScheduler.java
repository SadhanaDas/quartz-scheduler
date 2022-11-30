package jobDataMap;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class JobDataMapScheduler {
	
	public static void main(String[] args) throws SchedulerException {
		SchedulerFactory stdFact = new StdSchedulerFactory();
		Scheduler scheduler = stdFact.getScheduler();
		scheduler.start();

		JobDetail job = JobBuilder.newJob(JobDataMapClass.class)
				.withIdentity("Job1", "group1")
				.usingJobData("jobSays","Hello World !")
				.usingJobData("myFloatValue",3.141f)
				.build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger","group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(05)
						.repeatForever())
				.build();

		scheduler.scheduleJob(job, trigger);

		try {
			Thread.sleep(5000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Shutdown");
		scheduler.shutdown();
	}

}
