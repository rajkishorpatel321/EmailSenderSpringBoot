package com.tirlok.jobserchMail.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.tirlok.jobserchMail.DTO.EmailDTO;

@Component
@Aspect
public class LoggingAsecept {

	public static final Log LOGGER = LogFactory.getLog(LoggingAsecept.class);
	
	@After("execution(* com.tirlok.jobserchMail.service.MailService.sendEmail(..) )")
	public void after(JoinPoint jp) {
		EmailDTO email = (EmailDTO) jp.getArgs()[0];
		LOGGER.info("try to fatch mail id "+email.getRecipient());
	}
	
}


//com.tirlok.jobserchMail.service