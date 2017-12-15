package com.namami.service;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.namami.bo.EmailServiceRequest;
import com.namami.bo.RegisterUserRequest;
import com.namami.bo.UpdateUserProfileRequest;
import com.namami.common.CommonConstants;

/**
 * @author Anand Jha
 * 
 */
@Service ( "emailService" )
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(EmailServiceRequest emailServiceRequest) {
		
		
		// sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("namami160@gmail.com", "namami2016");
            }
        };
 
        Session session = /* Session.getDefaultInstance(properties, auth);*/Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        try {
			msg.setFrom(new InternetAddress("NAMAMI"));
		
        InternetAddress[] toAddresses = { new InternetAddress(emailServiceRequest.getMailTo()) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(emailServiceRequest.getSubject());
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(emailServiceRequest.getMessage().toString(), "text/html");
 
        // sends the e-mail
        Transport.send(msg);
        
        //Transport transport = session.getTransport("smtp");
        
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		//transport.connect("smtp.gmail.com", "rentlicence@gmail.com", "rentLicence@Pune");
		//transport.sendMessage(msg, msg.getAllRecipients());
		//transport.close();
        
        } catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	protected void addAtachments(String[] attachments, Multipart multipart)
            throws MessagingException, AddressException {
            
        for (int i = 0; i <= attachments.length - 1; i++) {
            String filename = attachments[i];

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            //use a JAF FileDataSource as it does MIME type detection
            DataSource source = new FileDataSource(filename);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(filename);
            //add the attachment
            multipart.addBodyPart(attachmentBodyPart);
                 
        }
	}

	public EmailServiceRequest createWelcomeEmailRequest(
			RegisterUserRequest userRequest) {
		
		EmailServiceRequest emailServiceRequest = new EmailServiceRequest();
		emailServiceRequest.setMailTo(userRequest.getEmail());
		emailServiceRequest.setSubject("Greetings from NAMAMI!!!!!");
		StringBuilder msg = new StringBuilder();
		
		msg.append("Dear Customer,<br>");
		msg.append("Thank you for selecting NAMAMI as your preferred choice.<br>");
		msg.append("This mail is to inform you that You have succesfully registered with us.<br>");
		msg.append("Please use below password to login:<br>");
		msg.append("Password: "+userRequest.getPassword()+"<br><br>");
		msg.append("Please do not reply back to this e-mail. For any queries or clarifications E-mail us by visiting www.namami.com/services.<br>");
		msg.append("<b>Thanks & Best Regards </b><br>");
		msg.append("<b>-NAMAMI Team </b><br>");
		emailServiceRequest.setMessage(msg);
		
		return emailServiceRequest;
	}

	@Override
	public EmailServiceRequest createUpdateUserProfileEmailRequest(UpdateUserProfileRequest updateUserProfileRequest) {
		EmailServiceRequest emailServiceRequest = new EmailServiceRequest();
		emailServiceRequest.setMailTo(updateUserProfileRequest.getUserEmail());
		emailServiceRequest.setSubject("Greetings from NAMAMI!!!!!");
		StringBuilder msg = new StringBuilder();
		
		msg.append("Dear Customer,<br>");
		msg.append("Thank you for selecting NAMAMI as your preferred choice.<br>");
		msg.append("This mail is to inform you that You have succesfully update your profile.<br>");
		if(null != updateUserProfileRequest.getIsMobChange() && updateUserProfileRequest.getIsMobChange().equals(CommonConstants.TRUE)){
			msg.append("Your updated Mobile :<br>");
			msg.append("Mobile Number: "+updateUserProfileRequest.getUserMobile()+"<br><br>");
		}
		if(null != updateUserProfileRequest.getIsPassChange() && updateUserProfileRequest.getIsPassChange().equals(CommonConstants.TRUE)){
			msg.append("Please use below updated password to login:<br>");
			msg.append("Updated Password: "+updateUserProfileRequest.getUserNewPassword()+"<br><br>");
		}
		msg.append("Please do not reply back to this e-mail. For any queries or clarifications E-mail us by visiting www.rentlicence.com/services.<br>");
		msg.append("<b>Thanks & Best Regards </b><br>");
		msg.append("<b>-NAMAMI Team </b><br>");
		emailServiceRequest.setMessage(msg);
		
		return emailServiceRequest;
	}	
	
	}
