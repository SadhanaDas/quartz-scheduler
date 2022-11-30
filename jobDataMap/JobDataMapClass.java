package jobDataMap;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class JobDataMapClass implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		
		JobKey key =context.getJobDetail().getKey();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String jobSays = dataMap.getString("jobSays");
		float myFloatValue = dataMap.getFloat("myFloatValue");
		
		System.out.println("Instance" +key+ "of JobDataMapClass says: " +jobSays+ ", and value is: " +myFloatValue);
	}

}
