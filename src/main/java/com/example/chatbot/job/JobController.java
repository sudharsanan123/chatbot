package com.example.chatbot.job;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.chatbot.entity.Notification;

@RestController
@RequestMapping("/scheduler")
public class JobController {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(JobController.class);

	@Autowired
	private EmailQuartzJobConfig quartzConfig;

	@PostMapping("/trigger-email-job")
	public String sendEmailJob(@RequestBody Notification notification) throws Exception {
		try {
			quartzConfig.scheduleEmailJob(notification);
			LOG.info("JobController.sendEmailJob({}) => Email sent sucessfully for player !!!!!!  " + notification.getPlayerId());
			return "Job scheduled successfully";
		} catch (SchedulerException e) {
			LOG.error("JobController.sendEmailJob({}) => Email failed to sent for player !!!!!!" + notification.getPlayerId());
			throw e;
		}
	}

}