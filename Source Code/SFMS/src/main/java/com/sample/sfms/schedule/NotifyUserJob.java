package com.sample.sfms.schedule;

import com.sample.sfms.service.interf.MailService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class NotifyUserJob implements Job {
    @Autowired
    private MailService mailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        mailService.notifyUser();
    }
}
