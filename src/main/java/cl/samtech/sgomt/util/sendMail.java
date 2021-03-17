package cl.samtech.sgomt.util;

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;


public class sendMail {
	final static Logger logger = Logger.getLogger(sendMail.class);
	public static void enviarCorreo(String Subject,String Mensage, String to)  {
		StringWriter errors = new StringWriter();
		Properties p = new Properties();
		try {
			p.load(new FileReader("/opt/config.properties"));
			//p.load(new FileReader("d:/config.properties"));
			
			
		} catch (Exception e) {
			e.printStackTrace(new PrintWriter(errors));
        	logger.error("Error al enviar correo=" + errors.toString());
		}
		final String Username=p.getProperty("Username");
		final String PassWord=p.getProperty("PassWord");
		String From=p.getProperty("From");
		String To=to;
		String SmtpServer=p.getProperty("SmtpServer");
		String port=p.getProperty("port");	
		
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", SmtpServer);
	    props.put("mail.smtp.port", port);

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(Username, PassWord);
	                }
	            });

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(From));
	        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(To));
	        message.setSubject(Subject);
	        message.setText(Mensage);
	        Transport.send(message);   

	    } catch (MessagingException e) {
	    	e.printStackTrace(new PrintWriter(errors));
        	logger.error("Error al enviar correo=" + errors.toString());
	    }
	}
		
}


