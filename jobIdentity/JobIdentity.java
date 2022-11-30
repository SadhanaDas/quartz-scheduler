package jobIdentity;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobIdentity implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		System.out.println("Identity Job");
		System.out.println("-----"+context.getJobDetail().getKey()
				+"executing.[" + new Date() + "]-----");
	}
}
