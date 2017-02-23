package com.hk.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
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
import javax.mail.internet.MimeMultipart;
import javax.activation.*;

import org.springframework.stereotype.Component;

import com.hk.common.email.EmailContentProperty;
import com.hk.common.exception.ServiceException;
import com.sun.xml.internal.ws.util.ByteArrayDataSource;

@Component
public class EmailSender {
	private final String USERNAME = "itplus.test@gmail.com";
	private final String PASSWORD = "itplus.test";
	private Properties props;
	private Session session;
	
	public EmailSender(){
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME, PASSWORD);
					}
				  });
	}
	
	public synchronized void sendTextOnly(EmailContentProperty ecp) throws IOException, ServiceException, AddressException, MessagingException{
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(USERNAME));
		message.setRecipients(Message.RecipientType.TO, ecp.getRecipients());
		
		message.setSubject(ecp.getSubject());
		
		//String content = "Click here to verify your email :"; 
		//message.setContent (content, "text/html; charset=ISO-8859-1");
		
		 // create the message part 
	    MimeBodyPart messageBodyPart =  new MimeBodyPart();

	    //fill message
	    messageBodyPart.setText(ecp.getText());

	    Multipart multipart = new MimeMultipart();
	    multipart.addBodyPart(messageBodyPart);
	   
	    // Put parts in message
	    message.setContent(multipart);
		Transport.send(message);
	
}
	
	public synchronized void sendAttachmentFromDir(String directoryAndFile, String attachmentName, EmailContentProperty ecp) throws IOException, ServiceException, AddressException, MessagingException{
		
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO, ecp.getRecipients());
			
			message.setSubject(ecp.getSubject());
			
			//String content = "Click here to verify your email :"; 
			//message.setContent (content, "text/html; charset=ISO-8859-1");
			
			 // create the message part 
		    MimeBodyPart messageBodyPart =  new MimeBodyPart();

		    //fill message
		    messageBodyPart.setText(ecp.getText());

		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
		   messageBodyPart = new MimeBodyPart();
		    DataSource source = new FileDataSource(directoryAndFile);
		    messageBodyPart.setDataHandler(new DataHandler(source));
		    messageBodyPart.setFileName(attachmentName);
		    multipart.addBodyPart(messageBodyPart);
		   
		    // Put parts in message
		    message.setContent(multipart);
			Transport.send(message);
		
	}
	
	public synchronized void sendAttachmentGeneratePDF(ByteArrayOutputStream baos, EmailContentProperty ecp) throws IOException, AddressException, MessagingException, ServiceException{
		
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(Message.RecipientType.TO, ecp.getRecipients());
			
			message.setSubject(ecp.getSubject());
			
			//String content = "Click here to verify your email :"; 
			//message.setContent (content, "text/html; charset=ISO-8859-1");
			
			 // create the message part 
		    MimeBodyPart messageBodyPart =  new MimeBodyPart();

		    //fill message
		    messageBodyPart.setText(ecp.getText());

		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
		    byte[] bytes = baos.toByteArray();
		    
		    messageBodyPart = new MimeBodyPart();
		    DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		    messageBodyPart.setDataHandler(new DataHandler(dataSource));
		    messageBodyPart.setFileName("test.pdf");
		    multipart.addBodyPart(messageBodyPart);
		   

		    // Put parts in message
		    message.setContent(multipart);
			Transport.send(message);
	}
}
