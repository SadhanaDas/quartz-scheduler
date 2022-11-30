package priority;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class PriorityJobScheduler {
	
	public static void main(String[] args) throws SchedulerException {
		SchedulerFactory stdFact = new StdSchedulerFactory();
		Scheduler scheduler = stdFact.getScheduler();

		JobDetail jobA = JobBuilder.newJob(PriorityJobClass.class)
				.withIdentity("JobA", "group1")
				.usingJobData("message","A")
				.usingJobData("priorityValue",10)
				.build();
		
		Trigger triggerA = TriggerBuilder.newTrigger()
				.withIdentity("TriggerA","group1")
				.startNow()
				.withPriority(10)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(10)
						.repeatForever())
				.build();
		
		JobDetail jobB = JobBuilder.newJob(PriorityJobClass.class)
				.withIdentity("JobB","group1")
				.usingJobData("message","B")
				.usingJobData("priorityValue",20)
				.build();
		
		Trigger triggerB = TriggerBuilder.newTrigger()
				.withIdentity("TriggerB","group1")
				.startNow()
				.withPriority(20)
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(05)
						.repeatForever())
				.build();
		
		System.out.println("\nScheduler Starts ....\n");
		scheduler.start();

		scheduler.scheduleJob(jobA, triggerA);
		scheduler.scheduleJob(jobB, triggerB);

		try {
			Thread.sleep(10000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\nShutdown");
		scheduler.shutdown();
	}


}
