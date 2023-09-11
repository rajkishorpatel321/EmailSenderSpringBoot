package com.tirlok.jobserchMail.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tirlok.jobserchMail.DTO.EmailDTO;
import com.tirlok.jobserchMail.dao.MailDao;
import com.tirlok.jobserchMail.service.MailService;

import jakarta.mail.MessagingException;

@Controller
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	MailDao mailDao;
	
	public static final Log LOGGER = LogFactory.getLog(MailController.class);

	@PostMapping("/sendMail")
	public String mail(@RequestParam String mail) throws MessagingException {
		//System.out.println("postmen hit this rest api");
		System.out.println(mail);
		LOGGER.info("this is info level log in the mail service");
		LOGGER.error(mail);
		
		EmailDTO details= new EmailDTO();
		details.setMsgBody("Hi Team,\r\n" + 
				"\r\n" + 
				"I hope this email finds you well. I am writing to express my interest in the Java Developer position advertised on LinkedIn. With two years of experience as a Java Developer, I believe that my skills and expertise align perfectly with the requirements of the role.\r\n " + 
				"\r\n" + 
				"Please find my attached resume, which provides additional details about my experience and qualifications. I would appreciate the opportunity to discuss my application further in an interview. I am available at your convenience for a call or meeting.\r\n" + 
				"\r\n" + 
				"Sincerely,\r\n" + 
				"Rajkishor Patel\r\n" + 
				"[Contact Information: 8103719631, rajkishorpatel321@gmail.com]");
		details.setRecipient(mail);
		details.setSubject("Application for Java Developer Position  - Rajkishor Patel");
		details.setAttachment("C:\\Users\\HP\\OneDrive\\Desktop\\resume2.0\\java_developer__.pdf");
		String status
        = mailService.sendEmail(details);

    return status;
	
		
	}
	
	@PostMapping("/bulkMail")
	public String bulkMail() {
		
		List<String > mailList = mailDao.mailList();
		
		for(String mail:mailList) {
			LOGGER.error(mail);
			EmailDTO details= new EmailDTO();
			details.setMsgBody("Hi Team,\r\n" + 
					"\r\n" + 
					"I hope this email finds you well. I am writing to express my interest in the Java Developer position advertised on LinkedIn. With two years of experience as a Java Developer, I believe that my skills and expertise align perfectly with the requirements of the role.\r\n " + 
					"\r\n" + 
					"Please find my attached resume, which provides additional details about my experience and qualifications. I would appreciate the opportunity to discuss my application further in an interview. I am available at your convenience for a call or meeting.\r\n" + 
					"\r\n" + 
					"Sincerely,\r\n" + 
					"Rajkishor Patel\r\n" + 
					"[Contact Information: 8103719631, rajkishorpatel321@gmail.com]");
			details.setRecipient(mail);
			details.setSubject("Application for Java Developer Position  - Rajkishor Patel");
			details.setAttachment("C:\\Users\\HP\\OneDrive\\Desktop\\resume2.0\\java_developer__.pdf");
			try {
				String status
				= mailService.sendEmail(details);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				LOGGER.error("Error in this mail ocuress "+mail);
				e.printStackTrace();
			}
		}
		
		
		
		return "all goind well";
		
	}
	
	
}
