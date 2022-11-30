package priority;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class PriorityJobClass implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		
		JobKey key =context.getJobDetail().getKey();
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String message = dataMap.getString("message");
		int priorityValue = dataMap.getInt("priorityValue");
		
		System.out.println("[" +new Date()+ "]"+" Instance " +key+ " of Job says: " +message+ ",and priority is: " +priorityValue);
	}

}
